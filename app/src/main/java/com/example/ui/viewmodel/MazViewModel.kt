package com.example.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.audio.AudioFeedbackManager
import com.example.audio.MandarinVoiceOption
import com.example.data.local.DailyActivityEntity
import com.example.data.local.MazDatabase
import com.example.data.local.PreferencesRepository
import com.example.data.local.StationProgressEntity
import com.example.data.local.SyllabusRepository
import com.example.data.local.UnlockedBadgeEntity
import com.example.data.local.UserPreferences
import com.example.data.model.AcademySubView
import com.example.data.model.AchievementBadge
import com.example.data.model.ChapterMeta
import com.example.data.model.FlashcardItem
import com.example.data.model.LevelId
import com.example.data.model.MainTab
import com.example.data.model.QuizItem
import com.example.data.model.RankInfo
import com.example.data.model.SpeedDrillQuestion
import com.example.data.model.StationMeta
import com.example.data.model.ToneExample
import com.example.domain.AchievementEngine
import com.example.domain.AchievementSnapshot
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class ArenaState(
    val levelId: LevelId = LevelId.T1,
    val chapterId: String = "c1",
    val stationId: String = "s1",
    val itemIndex: Int = 0,
    val flashcards: List<FlashcardItem> = emptyList(),
    val isCardFlipped: Boolean = false,
    val masteredCardIndices: Set<Int> = emptySet(),
    val quizItems: List<QuizItem> = emptyList(),
    val selectedOption: String? = null,
    val selectedOrderWords: List<String> = emptyList(),
    val isAnswered: Boolean = false,
    val isAnswerCorrect: Boolean = false,
    val feedbackDetail: String = "",
    val stationCorrectCount: Int = 0,
    val currentCombo: Int = 0,
    val bestCombo: Int = 0,
    val sessionXpEarned: Int = 0,
    val showCelebration: Boolean = false,
    val starsJustEarned: Int = 3
)

data class SpeedDrillUiState(
    val isActive: Boolean = false,
    val isFinished: Boolean = false,
    val questions: List<SpeedDrillQuestion> = emptyList(),
    val currentIndex: Int = 0,
    val score: Int = 0,
    val correctCount: Int = 0,
    val combo: Int = 0,
    val timeLeftSeconds: Int = 90,
    val selectedOption: String? = null,
    val isAnswered: Boolean = false,
    val isCorrect: Boolean = false
)

data class MazUiState(
    val activeTab: MainTab = MainTab.ACADEMY,
    val academySubView: AcademySubView = AcademySubView.CHAPTERS,
    val selectedChapterId: String = "c1",
    val prefs: UserPreferences = UserPreferences(),
    val chapters: List<ChapterMeta> = emptyList(),
    val stationsMeta: List<StationMeta> = emptyList(),
    val toneExamples: List<ToneExample> = emptyList(),
    val progressMap: Map<String, StationProgressEntity> = emptyMap(),
    val totalXp: Int = 0,
    val todayXp: Int = 0,
    val dailyGoalXp: Int = 100,
    val streakDays: Int = 0,
    val accuracyPercent: Int = 100,
    val rank: RankInfo = RankInfo("Pelajar Muda", "新星学员", 0, 150, "🌱"),
    val badges: List<AchievementBadge> = emptyList(),
    val nextClosestBadge: AchievementBadge? = null,
    val pendingBadgeUnlocks: List<AchievementBadge> = emptyList(),
    val levelStars: Int = 0,
    val levelCompletedCount: Int = 0,
    val arena: ArenaState = ArenaState(),
    val speedDrill: SpeedDrillUiState = SpeedDrillUiState(),
    val vocabQuery: String = "",
    val vocabResults: List<FlashcardItem> = emptyList(),
    val availableVoices: List<MandarinVoiceOption> = emptyList()
)

private data class NavSessionState(
    val activeTab: MainTab = MainTab.ACADEMY,
    val academySubView: AcademySubView = AcademySubView.CHAPTERS,
    val selectedChapterId: String = "c1",
    val vocabQuery: String = ""
)

class MazViewModel(application: Application) : AndroidViewModel(application) {

    private val database = MazDatabase.getInstance(application)
    private val dao = database.mazDao()
    private val prefsRepo = PreferencesRepository(application)
    val syllabusRepo = SyllabusRepository(application)
    val audioManager = AudioFeedbackManager(application)

    private val navState = MutableStateFlow(NavSessionState())
    private val arenaState = MutableStateFlow(ArenaState())
    private val speedDrillState = MutableStateFlow(SpeedDrillUiState())
    private val pendingUnlockQueue = MutableStateFlow<List<AchievementBadge>>(emptyList())
    private var drillTimerJob: Job? = null
    private var userActionOccurred: Boolean = false

    private val allFlashcards: List<FlashcardItem> by lazy { syllabusRepo.getAllFlashcards() }

    private val persistedFlow = combine(
        prefsRepo.preferencesFlow,
        dao.observeAllProgress(),
        dao.observeAllDailyActivity(),
        dao.observeUnlockedBadges(),
        audioManager.availableVoices
    ) { prefs, progressList, dailyList, unlockedBadges, voices ->
        audioManager.updateSettings(prefs.speechRate, prefs.preferredVoiceName)
        PersistedBundle(prefs, progressList, dailyList, unlockedBadges, voices)
    }

    init {
        viewModelScope.launch {
            persistedFlow.collect { persisted ->
                syncNewlyUnlockedBadges(persisted)
            }
        }
    }

    val uiState: StateFlow<MazUiState> = combine(
        persistedFlow,
        navState,
        arenaState,
        speedDrillState,
        pendingUnlockQueue
    ) { persisted, nav, arena, drill, unlockQueue ->
        val prefs = persisted.prefs
        val level = prefs.currentLevel
        val progressMap = persisted.progressList.associateBy { it.stationKey }
        val levelProgress = persisted.progressList.filter { it.levelId == level.key }
        val levelStars = levelProgress.sumOf { it.stars }
        val levelCompletedCount = levelProgress.size

        val totalXp = persisted.dailyList.sumOf { it.xpEarned }
        val todayKey = todayDateKey()
        val todayXp = persisted.dailyList.firstOrNull { it.dateKey == todayKey }?.xpEarned ?: 0
        val totalCorrect = persisted.dailyList.sumOf { it.correctAnswers }
        val totalAnswered = persisted.dailyList.sumOf { it.totalAnswers }
        val accuracy = if (totalAnswered > 0) ((totalCorrect * 100) / totalAnswered) else 100
        val streak = calculateStreak(persisted.dailyList)
        val rank = syllabusRepo.getRankForXp(totalXp)

        val snapshot = AchievementSnapshot(
            progressList = persisted.progressList,
            dailyActivities = persisted.dailyList,
            streakDays = streak,
            todayXp = todayXp,
            totalXp = totalXp,
            bestSpeedDrillScore = prefs.bestSpeedDrillScore,
            persistedUnlocks = persisted.unlockedBadges.associateBy { it.badgeId }
        )
        val badges = AchievementEngine.evaluate(snapshot)
        val nextClosestBadge = AchievementEngine.nextClosestBadge(badges)

        val query = nav.vocabQuery.trim().lowercase(Locale.ROOT)
        val filteredVocab = if (query.isEmpty()) {
            allFlashcards.filter { it.levelId == level }
        } else {
            allFlashcards.filter { card ->
                card.hanzi.contains(query, ignoreCase = true) ||
                    card.pinyin.contains(query, ignoreCase = true) ||
                    card.meaning.contains(query, ignoreCase = true) ||
                    card.exBm.contains(query, ignoreCase = true)
            }
        }

        MazUiState(
            activeTab = nav.activeTab,
            academySubView = nav.academySubView,
            selectedChapterId = nav.selectedChapterId,
            prefs = prefs,
            chapters = syllabusRepo.getChapters(level),
            stationsMeta = syllabusRepo.stationsMeta,
            toneExamples = syllabusRepo.toneExamples,
            progressMap = progressMap,
            totalXp = totalXp,
            todayXp = todayXp,
            streakDays = streak,
            accuracyPercent = accuracy,
            rank = rank,
            badges = badges,
            nextClosestBadge = nextClosestBadge,
            pendingBadgeUnlocks = unlockQueue,
            levelStars = levelStars,
            levelCompletedCount = levelCompletedCount,
            arena = arena,
            speedDrill = drill,
            vocabQuery = nav.vocabQuery,
            vocabResults = filteredVocab,
            availableVoices = persisted.voices
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = MazUiState(
            chapters = syllabusRepo.getChapters(LevelId.T1),
            stationsMeta = syllabusRepo.stationsMeta,
            toneExamples = syllabusRepo.toneExamples
        )
    )

    private data class PersistedBundle(
        val prefs: UserPreferences,
        val progressList: List<StationProgressEntity>,
        val dailyList: List<DailyActivityEntity>,
        val unlockedBadges: List<UnlockedBadgeEntity>,
        val voices: List<MandarinVoiceOption>
    )

    // =========================================================
    // NAVIGATION & LEVEL SELECTION
    // =========================================================
    fun selectMainTab(tab: MainTab) {
        audioManager.stopAllSfx()
        if (tab != MainTab.CHALLENGE) {
            cancelSpeedDrill()
        }
        navState.update {
            it.copy(
                activeTab = tab,
                academySubView = if (tab == MainTab.ACADEMY) AcademySubView.CHAPTERS else it.academySubView
            )
        }
    }

    fun selectLevel(level: LevelId) {
        audioManager.stopAllSfx()
        viewModelScope.launch { prefsRepo.setCurrentLevel(level) }
        navState.update { it.copy(selectedChapterId = "c1") }
    }

    fun selectChapterForPreview(chapterId: String) {
        navState.update { it.copy(selectedChapterId = chapterId) }
    }

    fun openChapterStations(chapterId: String) {
        audioManager.stopAllSfx()
        navState.update {
            it.copy(
                activeTab = MainTab.ACADEMY,
                selectedChapterId = chapterId,
                academySubView = AcademySubView.STATIONS
            )
        }
    }

    fun navigateBackFromStations() {
        audioManager.stopAllSfx()
        navState.update { it.copy(academySubView = AcademySubView.CHAPTERS) }
    }

    fun navigateBackFromArena(isTabletTwoPane: Boolean) {
        audioManager.stopAllSfx()
        navState.update {
            it.copy(
                academySubView = if (isTabletTwoPane) AcademySubView.CHAPTERS else AcademySubView.STATIONS
            )
        }
    }

    fun resumeLastActivity() {
        val prefs = uiState.value.prefs
        val level = if (prefs.lastLevelKey.isNotEmpty()) LevelId.fromKey(prefs.lastLevelKey) else prefs.currentLevel
        val chapterId = prefs.lastChapterId.ifEmpty { "c1" }
        val stationId = prefs.lastStationId.ifEmpty { "s1" }
        viewModelScope.launch { prefsRepo.setCurrentLevel(level) }
        openStation(level, chapterId, stationId, prefs.lastItemIndex.coerceIn(0, 4))
    }

    // =========================================================
    // ARENA: FLASHCARD & QUIZ (STESEN 1 - 7)
    // =========================================================
    fun openStation(levelId: LevelId, chapterId: String, stationId: String, startIndex: Int = 0) {
        audioManager.stopAllSfx()
        val meta = syllabusRepo.getStationMeta(stationId)
        val flashcards = if (meta.isFlashcard) syllabusRepo.getFlashcards(levelId, chapterId) else emptyList()
        val quizItems = if (!meta.isFlashcard) syllabusRepo.getQuizItems(levelId, chapterId, stationId) else emptyList()
        val maxIdx = (if (meta.isFlashcard) flashcards.size else quizItems.size).coerceAtLeast(1) - 1
        val safeIdx = startIndex.coerceIn(0, maxIdx)

        arenaState.value = ArenaState(
            levelId = levelId,
            chapterId = chapterId,
            stationId = stationId,
            itemIndex = safeIdx,
            flashcards = flashcards,
            quizItems = quizItems
        )
        navState.update {
            it.copy(
                activeTab = MainTab.ACADEMY,
                selectedChapterId = chapterId,
                academySubView = AcademySubView.ARENA
            )
        }
        viewModelScope.launch {
            prefsRepo.recordLastActivity(levelId, chapterId, stationId, safeIdx)
        }

        if (uiState.value.prefs.autoSpeak) {
            if (meta.isFlashcard) {
                flashcards.getOrNull(safeIdx)?.let { audioManager.speakMandarin(it.hanzi) }
            } else {
                quizItems.getOrNull(safeIdx)?.let { audioManager.speakMandarin(it.speech) }
            }
        }
    }

    fun toggleFlashcardFlip() {
        val current = arenaState.value
        val newFlipped = !current.isCardFlipped
        val newMastered = if (newFlipped) current.masteredCardIndices + current.itemIndex else current.masteredCardIndices
        val gainedXp = if (newFlipped && current.itemIndex !in current.masteredCardIndices) 5 else 0
        arenaState.update {
            it.copy(
                isCardFlipped = newFlipped,
                masteredCardIndices = newMastered,
                sessionXpEarned = it.sessionXpEarned + gainedXp
            )
        }
        if (gainedXp > 0) {
            recordDailyActivity(xpDelta = gainedXp)
        }
    }

    fun previousFlashcard() {
        val current = arenaState.value
        if (current.itemIndex <= 0) return
        val newIdx = current.itemIndex - 1
        arenaState.update { it.copy(itemIndex = newIdx, isCardFlipped = false) }
        viewModelScope.launch {
            prefsRepo.recordLastActivity(current.levelId, current.chapterId, current.stationId, newIdx)
        }
        if (uiState.value.prefs.autoSpeak) {
            current.flashcards.getOrNull(newIdx)?.let { audioManager.speakMandarin(it.hanzi) }
        }
    }

    fun nextFlashcard() {
        val current = arenaState.value
        if (current.itemIndex < current.flashcards.lastIndex) {
            val newIdx = current.itemIndex + 1
            arenaState.update { it.copy(itemIndex = newIdx, isCardFlipped = false) }
            viewModelScope.launch {
                prefsRepo.recordLastActivity(current.levelId, current.chapterId, current.stationId, newIdx)
            }
            if (uiState.value.prefs.autoSpeak) {
                current.flashcards.getOrNull(newIdx)?.let { audioManager.speakMandarin(it.hanzi) }
            }
        } else {
            completeCurrentStation()
        }
    }

    fun submitMcqAnswer(selectedOption: String) {
        val current = arenaState.value
        if (current.isAnswered) return
        val q = current.quizItems.getOrNull(current.itemIndex) ?: return
        val isCorrect = selectedOption == q.answer
        applyQuizAnswerResult(
            isCorrect = isCorrect,
            selectedOpt = selectedOption,
            speech = q.speech,
            detail = if (isCorrect) q.explanation else "Jawapan tepat ialah \"${q.answer}\". ${q.explanation}"
        )
    }

    fun toggleOrderWord(word: String, fromDropzoneIndex: Int? = null) {
        val current = arenaState.value
        if (current.isAnswered) return
        val updated = current.selectedOrderWords.toMutableList()
        if (fromDropzoneIndex != null && fromDropzoneIndex in updated.indices) {
            updated.removeAt(fromDropzoneIndex)
        } else if (word !in updated) {
            updated.add(word)
        }
        arenaState.update { it.copy(selectedOrderWords = updated) }
    }

    fun clearOrderWords() {
        if (arenaState.value.isAnswered) return
        arenaState.update { it.copy(selectedOrderWords = emptyList()) }
    }

    fun checkSentenceOrder() {
        val current = arenaState.value
        if (current.isAnswered || current.selectedOrderWords.isEmpty()) return
        val q = current.quizItems.getOrNull(current.itemIndex) ?: return
        val isCorrect = current.selectedOrderWords.joinToString("") == q.answerOrder.joinToString("")
        applyQuizAnswerResult(
            isCorrect = isCorrect,
            selectedOpt = null,
            speech = q.speech,
            detail = if (isCorrect) q.explanation else "Susunan tepat: ${q.answerOrder.joinToString(" ")}. ${q.explanation}"
        )
    }

    private fun applyQuizAnswerResult(
        isCorrect: Boolean,
        selectedOpt: String?,
        speech: String,
        detail: String
    ) {
        val current = arenaState.value
        val newCombo = if (isCorrect) current.currentCombo + 1 else 0
        val newBestCombo = maxOf(current.bestCombo, newCombo)
        val xpGain = if (isCorrect) 15 + (if (newCombo >= 2) (newCombo - 1) * 5 else 0) else 2
        val soundFx = uiState.value.prefs.soundFx

        if (isCorrect) {
            audioManager.playCorrectFeedback(soundFx, speech)
        } else {
            audioManager.playWrongFeedback(soundFx)
        }

        arenaState.update {
            it.copy(
                selectedOption = selectedOpt,
                isAnswered = true,
                isAnswerCorrect = isCorrect,
                feedbackDetail = detail,
                stationCorrectCount = it.stationCorrectCount + (if (isCorrect) 1 else 0),
                currentCombo = newCombo,
                bestCombo = newBestCombo,
                sessionXpEarned = it.sessionXpEarned + xpGain
            )
        }
        recordDailyActivity(
            xpDelta = xpGain,
            correctDelta = if (isCorrect) 1 else 0,
            totalDelta = 1
        )
    }

    fun nextQuizChallenge() {
        audioManager.stopAllSfx()
        val current = arenaState.value
        if (current.itemIndex < current.quizItems.lastIndex) {
            val newIdx = current.itemIndex + 1
            arenaState.update {
                it.copy(
                    itemIndex = newIdx,
                    selectedOption = null,
                    selectedOrderWords = emptyList(),
                    isAnswered = false,
                    isAnswerCorrect = false,
                    feedbackDetail = ""
                )
            }
            viewModelScope.launch {
                prefsRepo.recordLastActivity(current.levelId, current.chapterId, current.stationId, newIdx)
            }
            if (uiState.value.prefs.autoSpeak) {
                current.quizItems.getOrNull(newIdx)?.let { audioManager.speakMandarin(it.speech) }
            }
        } else {
            completeCurrentStation()
        }
    }

    private fun completeCurrentStation() {
        val current = arenaState.value
        val isFlashcard = syllabusRepo.getStationMeta(current.stationId).isFlashcard
        val total = if (isFlashcard) current.flashcards.size else current.quizItems.size
        val score = if (isFlashcard) total else current.stationCorrectCount
        val stars = when {
            isFlashcard -> 3
            score >= 4 -> 3
            score >= 2 -> 2
            else -> 1
        }
        val bonusXp = 50
        val key = "${current.levelId.key}_${current.chapterId}_${current.stationId}"

        viewModelScope.launch {
            val existing = dao.getProgressByKey(key)
            val bestStars = maxOf(existing?.stars ?: 0, stars)
            val bestScore = maxOf(existing?.bestScore ?: 0, score)
            dao.upsertProgress(
                StationProgressEntity(
                    stationKey = key,
                    levelId = current.levelId.key,
                    chapterId = current.chapterId,
                    stationId = current.stationId,
                    stars = bestStars,
                    bestScore = bestScore,
                    totalQuestions = total
                )
            )
            recordDailyActivityInternal(xpDelta = bonusXp, stationsDelta = if (existing == null) 1 else 0)

            val stIdx = syllabusRepo.stationsMeta.indexOfFirst { it.id == current.stationId }
            val nextStation = syllabusRepo.stationsMeta.getOrNull(stIdx + 1) ?: syllabusRepo.stationsMeta.first()
            prefsRepo.recordLastActivity(current.levelId, current.chapterId, nextStation.id, 0)
        }

        audioManager.playCorrectFeedback(uiState.value.prefs.soundFx)
        arenaState.update {
            it.copy(
                sessionXpEarned = it.sessionXpEarned + bonusXp,
                starsJustEarned = stars,
                showCelebration = true
            )
        }
    }

    fun dismissCelebrationAndProceed(openNextStation: Boolean, isTabletTwoPane: Boolean) {
        val current = arenaState.value
        arenaState.update { it.copy(showCelebration = false) }
        if (openNextStation) {
            val stIdx = syllabusRepo.stationsMeta.indexOfFirst { it.id == current.stationId }
            val nextStation = syllabusRepo.stationsMeta.getOrNull(stIdx + 1)
            if (nextStation != null) {
                openStation(current.levelId, current.chapterId, nextStation.id, 0)
                return
            }
        }
        navigateBackFromArena(isTabletTwoPane)
    }

    // =========================================================
    // SPEED DRILL (CABARAN KILAT 10 SOALAN) & VOCAB SEARCH
    // =========================================================
    fun startSpeedDrill() {
        audioManager.stopAllSfx()
        drillTimerJob?.cancel()
        val level = uiState.value.prefs.currentLevel
        val questions = syllabusRepo.generateSpeedDrill(level, 10)
        speedDrillState.value = SpeedDrillUiState(
            isActive = true,
            isFinished = false,
            questions = questions,
            currentIndex = 0,
            score = 0,
            correctCount = 0,
            combo = 0,
            timeLeftSeconds = 90
        )
        drillTimerJob = viewModelScope.launch {
            while (speedDrillState.value.isActive && !speedDrillState.value.isFinished && speedDrillState.value.timeLeftSeconds > 0) {
                delay(1000L)
                speedDrillState.update { s ->
                    val nextTime = (s.timeLeftSeconds - 1).coerceAtLeast(0)
                    if (nextTime == 0) {
                        finishSpeedDrillInternal(s.copy(timeLeftSeconds = 0))
                    } else {
                        s.copy(timeLeftSeconds = nextTime)
                    }
                }
            }
        }
    }

    fun answerSpeedDrill(option: String) {
        val state = speedDrillState.value
        if (!state.isActive || state.isAnswered || state.isFinished) return
        val q = state.questions.getOrNull(state.currentIndex)?.quizItem ?: return
        val isCorrect = option == q.answer
        val newCombo = if (isCorrect) state.combo + 1 else 0
        val points = if (isCorrect) 100 + (newCombo - 1) * 25 else 0

        if (isCorrect) {
            audioManager.playCorrectFeedback(uiState.value.prefs.soundFx)
        } else {
            audioManager.playWrongFeedback(uiState.value.prefs.soundFx)
        }

        recordDailyActivity(
            xpDelta = if (isCorrect) 12 else 2,
            correctDelta = if (isCorrect) 1 else 0,
            totalDelta = 1
        )

        speedDrillState.update {
            it.copy(
                selectedOption = option,
                isAnswered = true,
                isCorrect = isCorrect,
                score = it.score + points,
                correctCount = it.correctCount + (if (isCorrect) 1 else 0),
                combo = newCombo
            )
        }

        viewModelScope.launch {
            delay(850L)
            val latest = speedDrillState.value
            if (!latest.isActive || latest.isFinished) return@launch
            if (latest.currentIndex < latest.questions.lastIndex) {
                speedDrillState.update {
                    it.copy(
                        currentIndex = it.currentIndex + 1,
                        selectedOption = null,
                        isAnswered = false,
                        isCorrect = false
                    )
                }
            } else {
                speedDrillState.update { finishSpeedDrillInternal(it) }
            }
        }
    }

    private fun finishSpeedDrillInternal(s: SpeedDrillUiState): SpeedDrillUiState {
        drillTimerJob?.cancel()
        val timeBonus = s.timeLeftSeconds * 3
        val finalScore = s.score + if (s.correctCount >= 5) timeBonus else 0
        viewModelScope.launch {
            prefsRepo.updateBestSpeedDrillScore(finalScore)
            recordDailyActivityInternal(xpDelta = 30)
        }
        return s.copy(isFinished = true, score = finalScore)
    }

    fun cancelSpeedDrill() {
        drillTimerJob?.cancel()
        audioManager.stopAllSfx()
        speedDrillState.value = SpeedDrillUiState()
    }

    fun updateVocabQuery(query: String) {
        navState.update { it.copy(vocabQuery = query) }
    }

    // =========================================================
    // AUDIO & SETTINGS
    // =========================================================
    fun speakMandarin(text: String, slow: Boolean = false) {
        val rate = if (slow) 0.65f else uiState.value.prefs.speechRate
        audioManager.speakMandarin(text, customRate = rate)
    }

    fun setShowPinyin(enabled: Boolean) {
        viewModelScope.launch { prefsRepo.setShowPinyin(enabled) }
    }

    fun setSoundFx(enabled: Boolean) {
        viewModelScope.launch { prefsRepo.setSoundFx(enabled) }
    }

    fun setAutoSpeak(enabled: Boolean) {
        viewModelScope.launch { prefsRepo.setAutoSpeak(enabled) }
    }

    fun setDarkTheme(enabled: Boolean) {
        viewModelScope.launch { prefsRepo.setDarkTheme(enabled) }
    }

    fun setSpeechRate(rate: Float) {
        viewModelScope.launch { prefsRepo.setSpeechRate(rate) }
    }

    fun setPreferredVoice(voiceName: String) {
        viewModelScope.launch { prefsRepo.setPreferredVoice(voiceName) }
    }

    fun saveStudentProfile(name: String, schoolCode: String) {
        viewModelScope.launch { prefsRepo.setStudentProfile(name, schoolCode) }
    }

    fun resetCurrentLevelProgress() {
        val level = uiState.value.prefs.currentLevel
        viewModelScope.launch {
            dao.clearProgressForLevel(level.key)
            prefsRepo.clearLastActivityIfMatchesLevel(level)
        }
    }

    // =========================================================
    // DAILY ACTIVITY, STREAK, BADGES & REPORTS
    // =========================================================
    private fun recordDailyActivity(
        xpDelta: Int = 0,
        stationsDelta: Int = 0,
        correctDelta: Int = 0,
        totalDelta: Int = 0
    ) {
        userActionOccurred = true
        viewModelScope.launch {
            recordDailyActivityInternal(xpDelta, stationsDelta, correctDelta, totalDelta)
        }
    }

    private suspend fun recordDailyActivityInternal(
        xpDelta: Int = 0,
        stationsDelta: Int = 0,
        correctDelta: Int = 0,
        totalDelta: Int = 0
    ) {
        userActionOccurred = true
        val key = todayDateKey()
        val existing = dao.getDailyActivity(key) ?: DailyActivityEntity(dateKey = key)
        dao.upsertDailyActivity(
            existing.copy(
                xpEarned = existing.xpEarned + xpDelta,
                stationsCompleted = existing.stationsCompleted + stationsDelta,
                correctAnswers = existing.correctAnswers + correctDelta,
                totalAnswers = existing.totalAnswers + totalDelta
            )
        )
    }

    private suspend fun syncNewlyUnlockedBadges(persisted: PersistedBundle) {
        val persistedMap = persisted.unlockedBadges.associateBy { it.badgeId }
        val totalXp = persisted.dailyList.sumOf { it.xpEarned }
        val todayKey = todayDateKey()
        val todayXp = persisted.dailyList.firstOrNull { it.dateKey == todayKey }?.xpEarned ?: 0
        val streak = calculateStreak(persisted.dailyList)

        val snapshot = AchievementSnapshot(
            progressList = persisted.progressList,
            dailyActivities = persisted.dailyList,
            streakDays = streak,
            todayXp = todayXp,
            totalXp = totalXp,
            bestSpeedDrillScore = persisted.prefs.bestSpeedDrillScore,
            persistedUnlocks = persistedMap
        )
        val evaluated = AchievementEngine.evaluate(snapshot)
        val newlyMet = evaluated.filter { it.isUnlocked && it.id !in persistedMap }
        if (newlyMet.isEmpty()) return

        val now = System.currentTimeMillis()
        var totalRewardXp = 0
        val newlyInsertedBadges = mutableListOf<AchievementBadge>()

        for (badge in newlyMet) {
            val rowId = dao.insertUnlockedBadge(
                UnlockedBadgeEntity(
                    badgeId = badge.id,
                    unlockedAt = now
                )
            )
            if (rowId != -1L) {
                totalRewardXp += badge.xpReward
                newlyInsertedBadges.add(badge.copy(unlockedAt = now))
            }
        }

        if (newlyInsertedBadges.isNotEmpty() && userActionOccurred) {
            if (totalRewardXp > 0) {
                val key = todayDateKey()
                val existing = dao.getDailyActivity(key) ?: DailyActivityEntity(dateKey = key)
                dao.upsertDailyActivity(
                    existing.copy(xpEarned = existing.xpEarned + totalRewardXp)
                )
            }
            audioManager.playCorrectFeedback(persisted.prefs.soundFx)
            pendingUnlockQueue.update { currentQueue ->
                val existingIds = currentQueue.map { it.id }.toSet()
                currentQueue + newlyInsertedBadges.filter { it.id !in existingIds }
            }
        }
    }

    fun dismissTopBadgeUnlock() {
        pendingUnlockQueue.update { queue ->
            if (queue.isNotEmpty()) queue.drop(1) else emptyList()
        }
    }

    fun viewTrophiesFromCelebration() {
        pendingUnlockQueue.value = emptyList()
        selectMainTab(MainTab.PROGRESS)
    }

    private fun todayDateKey(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        return sdf.format(Date())
    }

    private fun calculateStreak(activities: List<DailyActivityEntity>): Int {
        val activeDates = activities.filter { it.xpEarned > 0 }.map { it.dateKey }.toSet()
        if (activeDates.isEmpty()) return 0
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val cal = Calendar.getInstance()
        val today = sdf.format(cal.time)
        if (today !in activeDates) {
            cal.add(Calendar.DAY_OF_YEAR, -1)
            if (sdf.format(cal.time) !in activeDates) return 0
        }
        var streak = 0
        while (sdf.format(cal.time) in activeDates) {
            streak++
            cal.add(Calendar.DAY_OF_YEAR, -1)
        }
        return streak
    }

    fun buildPlainTextReport(): String {
        val state = uiState.value
        val level = state.prefs.currentLevel
        val unlockedBadges = state.badges.filter { it.isUnlocked }
        val dateStr = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())
        return buildString {
            appendLine("📘 REKOD PRESTASI MAZ MANDARIN AKADEMIK (BCK KSSM)")
            appendLine("Tarikh: $dateStr")
            appendLine("Murid: ${state.prefs.studentName}" + if (state.prefs.schoolCode.isNotBlank()) " (${state.prefs.schoolCode})" else "")
            appendLine("Tahap: ${level.label} | Pangkat: ${state.rank.emoji} ${state.rank.titleBm}")
            appendLine("----------------------------------------")
            appendLine("⭐ Bintang (${level.shortLabel}): ${state.levelStars}")
            appendLine("✅ Stesen Selesai: ${state.levelCompletedCount}/49")
            appendLine("⚡ Jumlah XP: ${state.totalXp} XP | Ketepatan: ${state.accuracyPercent}%")
            appendLine("🔥 Rentetan Harian: ${state.streakDays} hari")
            appendLine("🏅 Lencana Pencapaian: ${unlockedBadges.size}/${state.badges.size}")
            if (unlockedBadges.isNotEmpty()) {
                appendLine("   (${unlockedBadges.joinToString(", ") { "${it.emoji} ${it.title}" }})")
            }
            appendLine("----------------------------------------")
            appendLine("Pecahan Mengikut Bab:")
            state.chapters.forEach { chap ->
                val done = state.stationsMeta.count { st ->
                    state.progressMap.containsKey("${level.key}_${chap.id}_${st.id}")
                }
                appendLine("• ${chap.numLabel}: ${chap.title} — $done/7 Stesen")
            }
        }
    }

    fun buildPrintableHtmlReport(): String {
        val state = uiState.value
        val level = state.prefs.currentLevel
        val unlockedCount = state.badges.count { it.isUnlocked }
        val dateStr = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date())
        val rows = state.chapters.joinToString("") { chap ->
            val done = state.stationsMeta.count { st ->
                state.progressMap.containsKey("${level.key}_${chap.id}_${st.id}")
            }
            val pct = (done * 100) / 7
            """
            <tr>
              <td><strong>${chap.numLabel}</strong>: ${chap.title}</td>
              <td style="text-align:center;">$done / 7 Stesen</td>
              <td style="text-align:right;">$pct%</td>
            </tr>
            """.trimIndent()
        }
        return """
        <!DOCTYPE html>
        <html>
        <head>
          <meta charset="utf-8"/>
          <style>
            body { font-family: sans-serif; color: #0f172a; padding: 24px; }
            .banner { background: #0f172a; color: #fff; padding: 20px; border-radius: 14px; }
            .banner h1 { margin: 0 0 4px 0; font-size: 20px; }
            .meta { font-size: 13px; color: #cbd5e1; }
            .stats { display: flex; gap: 12px; margin: 18px 0; }
            .stat { flex: 1; background: #eef2ff; padding: 12px; border-radius: 10px; }
            .stat span { display: block; font-size: 11px; color: #475569; }
            .stat strong { font-size: 18px; color: #3730a3; }
            table { width: 100%; border-collapse: collapse; margin-top: 12px; }
            th, td { border-bottom: 1px solid #e2e8f0; padding: 10px 8px; font-size: 13px; text-align: left; }
            th { background: #f8fafc; }
          </style>
        </head>
        <body>
          <div class="banner">
            <h1>MAZ MANDARIN AKADEMIK · BCK KSSM</h1>
            <div class="meta">Rekod Prestasi Murid · ${level.label} · ${state.prefs.studentName} · $dateStr</div>
          </div>
          <div class="stats">
            <div class="stat"><span>Stesen Selesai</span><strong>${state.levelCompletedCount} / 49</strong></div>
            <div class="stat"><span>Bintang (${level.shortLabel})</span><strong>⭐ ${state.levelStars}</strong></div>
            <div class="stat"><span>Jumlah XP</span><strong>${state.totalXp} XP</strong></div>
            <div class="stat"><span>Lencana</span><strong>🏅 $unlockedCount / ${state.badges.size}</strong></div>
          </div>
          <h3>Pecahan Kemajuan Mengikut Bab (${level.label})</h3>
          <table>
            <thead><tr><th>Bab</th><th style="text-align:center;">Kemajuan</th><th style="text-align:right;">Peratus</th></tr></thead>
            <tbody>$rows</tbody>
          </table>
        </body>
        </html>
        """.trimIndent()
    }

    override fun onCleared() {
        super.onCleared()
        drillTimerJob?.cancel()
        audioManager.release()
    }
}
