package com.example.data.model

enum class LevelId(val key: String, val label: String, val shortLabel: String) {
    T1("t1", "Tingkatan 1", "T1"),
    T2("t2", "Tingkatan 2", "T2"),
    T3("t3", "Tingkatan 3", "T3");

    companion object {
        fun fromKey(key: String): LevelId = entries.firstOrNull { it.key == key } ?: T1
    }
}

enum class MainTab {
    ACADEMY,
    CHALLENGE,
    PROGRESS,
    SETTINGS
}

enum class AcademySubView {
    CHAPTERS,
    STATIONS,
    ARENA
}

data class StationMeta(
    val id: String,
    val stepLabel: String,
    val icon: String,
    val title: String,
    val desc: String,
    val isFlashcard: Boolean = false
)

data class ChapterMeta(
    val id: String,
    val numLabel: String,
    val emoji: String,
    val title: String,
    val hanzi: String,
    val desc: String
)

data class FlashcardItem(
    val emoji: String,
    val hanzi: String,
    val pinyin: String,
    val meaning: String,
    val exCn: String,
    val exPy: String,
    val exBm: String,
    val levelId: LevelId = LevelId.T1,
    val chapterNum: String = ""
)

enum class QuizType {
    MCQ,
    ORDER,
    COMPREHENSION
}

data class QuizItem(
    val type: QuizType,
    val emoji: String = "",
    val instruction: String,
    val hanzi: String = "",
    val pinyin: String = "",
    val speech: String = "",
    val options: List<String> = emptyList(),
    val answer: String = "",
    val promptText: String = "",
    val words: List<String> = emptyList(),
    val answerOrder: List<String> = emptyList(),
    val passage: String = "",
    val question: String = "",
    val explanation: String = "",
    val sourceTag: String = ""
)

data class RankInfo(
    val titleBm: String,
    val titleCn: String,
    val minXp: Int,
    val nextXp: Int,
    val emoji: String
)

enum class BadgeCategory(val labelBm: String, val iconEmoji: String) {
    MILESTONE("Silibus", "🗺️"),
    MASTERY("Penguasaan", "🎯"),
    CONSISTENCY("Konsistensi", "🔥"),
    CHALLENGE("Cabaran", "⚡")
}

enum class BadgeTier(val labelBm: String) {
    BRONZE("Gangsa"),
    SILVER("Perak"),
    GOLD("Emas"),
    JADE("Jed Imperial")
}

data class AchievementBadge(
    val id: String,
    val emoji: String,
    val hanziSeal: String,
    val title: String,
    val desc: String,
    val milestoneTip: String,
    val category: BadgeCategory,
    val tier: BadgeTier,
    val currentValue: Int,
    val targetValue: Int,
    val unitLabel: String,
    val xpReward: Int,
    val isUnlocked: Boolean,
    val unlockedAt: Long? = null
) {
    val progressFraction: Float
        get() = if (targetValue <= 0) 0f else (currentValue.toFloat() / targetValue.toFloat()).coerceIn(0f, 1f)

    val progressText: String
        get() = "$currentValue/$targetValue $unitLabel"
}

data class ToneExample(
    val toneNumber: Int,
    val mark: String,
    val labelBm: String,
    val contourDesc: String,
    val hanzi: String,
    val pinyin: String,
    val meaningBm: String,
    val speech: String
)

data class SpeedDrillQuestion(
    val quizItem: QuizItem,
    val chapterTitle: String
)
