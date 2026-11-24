package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Backspace
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.FlashcardItem
import com.example.data.model.QuizItem
import com.example.data.model.QuizType
import com.example.ui.components.AudioChipButton
import com.example.ui.components.StationCelebrationDialog
import com.example.ui.components.TactileCard
import com.example.ui.components.TactilePrimaryButton
import com.example.ui.components.TactileSecondaryButton
import com.example.ui.theme.CoralCrimson
import com.example.ui.theme.IndigoMid
import com.example.ui.theme.LocalMazColors
import com.example.ui.viewmodel.ArenaState
import com.example.ui.viewmodel.MazUiState

@Composable
fun ArenaScreen(
    uiState: MazUiState,
    isTabletTwoPane: Boolean,
    onBack: () -> Unit,
    onSpeak: (String, Boolean) -> Unit,
    onFlipCard: () -> Unit,
    onPrevCard: () -> Unit,
    onNextCard: () -> Unit,
    onSelectMcq: (String) -> Unit,
    onToggleOrderWord: (String, Int?) -> Unit,
    onClearOrderWords: () -> Unit,
    onCheckOrder: () -> Unit,
    onNextChallenge: () -> Unit,
    onCelebrationAction: (Boolean) -> Unit
) {
    BackHandler(onBack = onBack)

    val arena = uiState.arena
    val stationMeta = uiState.stationsMeta.firstOrNull { it.id == arena.stationId }
        ?: uiState.stationsMeta.first()
    val totalCount = if (stationMeta.isFlashcard) arena.flashcards.size else arena.quizItems.size
    val currentNum = (arena.itemIndex + 1).coerceAtMost(totalCount.coerceAtLeast(1))
    val progressFraction = if (totalCount > 0) currentNum.toFloat() / totalCount.toFloat() else 0f
    val maz = LocalMazColors.current

    if (arena.showCelebration) {
        val currentIdx = uiState.stationsMeta.indexOfFirst { it.id == arena.stationId }
        StationCelebrationDialog(
            stationTitle = "${stationMeta.icon} ${stationMeta.title}",
            starsEarned = arena.starsJustEarned,
            xpEarned = arena.sessionXpEarned,
            bestCombo = arena.bestCombo,
            hasNextStation = currentIdx in 0 until uiState.stationsMeta.lastIndex,
            onNextStation = { onCelebrationAction(true) },
            onBackToStations = { onCelebrationAction(false) }
        )
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 720.dp)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 16.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Top Arena Breadcrumb Bar
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Surface(
                    modifier = Modifier
                        .heightIn(min = 48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .testTag("arena_back_btn")
                        .clickable(onClick = onBack),
                    shape = RoundedCornerShape(14.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(1.5.dp, maz.cardBorder)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Kembali",
                            tint = MaterialTheme.colorScheme.onSurface,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(Modifier.width(5.dp))
                        Text(
                            text = if (isTabletTwoPane) "Akademi" else "7 Stesen",
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                }

                Text(
                    text = "${stationMeta.icon} ${stationMeta.title}",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onBackground,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 10.dp),
                    textAlign = TextAlign.Center
                )

                Surface(
                    shape = CircleShape,
                    color = maz.jadeSoft
                ) {
                    Text(
                        text = "$currentNum/$totalCount",
                        style = MaterialTheme.typography.labelMedium,
                        color = maz.onJadeSoft,
                        modifier = Modifier.padding(horizontal = 11.dp, vertical = 6.dp)
                    )
                }
            }

            // Progress Bar + Live Combo Pill
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                LinearProgressIndicator(
                    progress = { progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(9.dp)
                        .clip(CircleShape),
                    color = CoralCrimson,
                    trackColor = maz.cardBorder
                )
                if (arena.currentCombo >= 2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Surface(shape = CircleShape, color = maz.amberSoft) {
                            Row(
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Rounded.LocalFireDepartment,
                                    contentDescription = null,
                                    tint = maz.amberDark,
                                    modifier = Modifier.size(14.dp)
                                )
                                Spacer(Modifier.width(3.dp))
                                Text(
                                    text = "${arena.currentCombo}x Kombo Pantas!",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = maz.onAmberSoft
                                )
                            }
                        }
                    }
                }
            }

            // Stage Content
            if (stationMeta.isFlashcard) {
                val card = arena.flashcards.getOrNull(arena.itemIndex)
                if (card != null) {
                    Flashcard3DStage(
                        card = card,
                        isFlipped = arena.isCardFlipped,
                        isFirst = arena.itemIndex == 0,
                        isLast = arena.itemIndex >= arena.flashcards.lastIndex,
                        onFlip = onFlipCard,
                        onSpeakNormal = { onSpeak(card.hanzi, false) },
                        onSpeakSlow = { onSpeak(card.hanzi, true) },
                        onSpeakExample = { onSpeak(card.exCn, false) },
                        onPrev = onPrevCard,
                        onNext = onNextCard
                    )
                }
            } else {
                val quizItem = arena.quizItems.getOrNull(arena.itemIndex)
                if (quizItem != null) {
                    QuizStageCard(
                        item = quizItem,
                        arena = arena,
                        showPinyin = uiState.prefs.showPinyin,
                        totalQuestions = totalCount,
                        onSpeakNormal = { onSpeak(quizItem.speech, false) },
                        onSpeakSlow = { onSpeak(quizItem.speech, true) },
                        onSelectMcq = onSelectMcq,
                        onToggleOrderWord = onToggleOrderWord,
                        onClearOrderWords = onClearOrderWords,
                        onCheckOrder = onCheckOrder,
                        onNextChallenge = onNextChallenge
                    )
                }
            }

            Spacer(Modifier.height(16.dp))
        }
    }
}

@Composable
private fun Flashcard3DStage(
    card: FlashcardItem,
    isFlipped: Boolean,
    isFirst: Boolean,
    isLast: Boolean,
    onFlip: () -> Unit,
    onSpeakNormal: () -> Unit,
    onSpeakSlow: () -> Unit,
    onSpeakExample: () -> Unit,
    onPrev: () -> Unit,
    onNext: () -> Unit
) {
    val maz = LocalMazColors.current
    val haptics = LocalHapticFeedback.current
    val density = LocalDensity.current.density

    val rotation by animateFloatAsState(
        targetValue = if (isFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 480, easing = FastOutSlowInEasing),
        label = "flashcard3dFlip"
    )

    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(370.dp)
                .testTag("flashcard_3d_box")
                .graphicsLayer {
                    rotationY = rotation
                    cameraDistance = 14f * density
                }
                .clip(RoundedCornerShape(26.dp))
                .clickable {
                    haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                    onFlip()
                }
        ) {
            if (rotation <= 90f) {
                // Front Face
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    shape = RoundedCornerShape(26.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(2.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f)),
                    shadowElevation = 6.dp
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(22.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Surface(shape = CircleShape, color = maz.indigoSoft) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(Icons.Rounded.Refresh, contentDescription = null, tint = maz.onIndigoSoft, modifier = Modifier.size(14.dp))
                                Text(
                                    text = "KAD IMBAS · KETIK UNTUK TERJEMAHAN",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = maz.onIndigoSoft
                                )
                            }
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(text = card.emoji, fontSize = 68.sp)
                            Spacer(Modifier.height(8.dp))
                            Text(
                                text = card.hanzi,
                                fontSize = 46.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = card.pinyin,
                                style = MaterialTheme.typography.titleLarge,
                                color = CoralCrimson,
                                fontWeight = FontWeight.ExtraBold
                            )
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            AudioChipButton(
                                label = "Dengar",
                                onClick = onSpeakNormal,
                                modifier = Modifier.testTag("fc_speak_btn")
                            )
                            AudioChipButton(
                                label = "0.7x Perlahan",
                                onClick = onSpeakSlow
                            )
                        }
                    }
                }
            } else {
                // Back Face (un-mirrored via rotationY = 180f)
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .graphicsLayer { rotationY = 180f },
                    shape = RoundedCornerShape(26.dp),
                    color = MaterialTheme.colorScheme.surface,
                    border = BorderStroke(2.dp, maz.jade.copy(alpha = 0.6f)),
                    shadowElevation = 6.dp
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(maz.indigoSoft.copy(alpha = 0.65f), MaterialTheme.colorScheme.surface)
                                )
                            )
                            .padding(22.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Surface(shape = CircleShape, color = maz.jadeSoft) {
                                Text(
                                    text = "MAKSUD & PENGGUNAAN AYAT",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = maz.onJadeSoft,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                                )
                            }

                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = card.meaning,
                                    style = MaterialTheme.typography.headlineMedium,
                                    color = MaterialTheme.colorScheme.primary,
                                    textAlign = TextAlign.Center
                                )
                                Spacer(Modifier.height(14.dp))
                                Surface(
                                    modifier = Modifier.fillMaxWidth(),
                                    shape = RoundedCornerShape(16.dp),
                                    color = MaterialTheme.colorScheme.surface,
                                    border = BorderStroke(1.5.dp, maz.cardBorder)
                                ) {
                                    Column(
                                        modifier = Modifier.padding(14.dp),
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Text(
                                            text = card.exCn,
                                            fontSize = 20.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface,
                                            textAlign = TextAlign.Center
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            text = card.exPy,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = maz.jade,
                                            fontWeight = FontWeight.Bold,
                                            textAlign = TextAlign.Center
                                        )
                                        Spacer(Modifier.height(4.dp))
                                        Text(
                                            text = card.exBm,
                                            style = MaterialTheme.typography.bodySmall,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                AudioChipButton(
                                    label = "Sebut Ayat Contoh",
                                    onClick = onSpeakExample
                                )
                            }
                        }
                    }
                }
            }
        }

        // Flashcard Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            TactileSecondaryButton(
                text = "← Sebelum",
                enabled = !isFirst,
                onClick = onPrev,
                modifier = Modifier
                    .weight(0.9f)
                    .testTag("fc_prev_btn")
            )
            TactilePrimaryButton(
                text = if (isLast) "Tamat & Kumpul ⭐" else "Kad Seterusnya →",
                onClick = onNext,
                modifier = Modifier
                    .weight(1.3f)
                    .testTag("fc_next_btn")
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun QuizStageCard(
    item: QuizItem,
    arena: ArenaState,
    showPinyin: Boolean,
    totalQuestions: Int,
    onSpeakNormal: () -> Unit,
    onSpeakSlow: () -> Unit,
    onSelectMcq: (String) -> Unit,
    onToggleOrderWord: (String, Int?) -> Unit,
    onClearOrderWords: () -> Unit,
    onCheckOrder: () -> Unit,
    onNextChallenge: () -> Unit
) {
    val maz = LocalMazColors.current
    val haptics = LocalHapticFeedback.current

    TactileCard(
        cornerRadius = 24.dp,
        depth = 5.dp
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            Surface(shape = CircleShape, color = maz.coralSoft) {
                Text(
                    text = "CABARAN ${arena.itemIndex + 1} DARIPADA $totalQuestions",
                    style = MaterialTheme.typography.labelSmall,
                    color = maz.onCoralSoft,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                )
            }

            Text(
                text = item.instruction,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center
            )

            // Prompt Display Box
            Surface(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                color = maz.subtleSurface,
                border = BorderStroke(1.5.dp, maz.cardBorder)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    if (item.emoji.isNotBlank()) {
                        Text(text = item.emoji, fontSize = 44.sp)
                        Spacer(Modifier.height(6.dp))
                    }
                    when (item.type) {
                        QuizType.COMPREHENSION -> {
                            Text(
                                text = item.passage,
                                fontSize = 19.sp,
                                lineHeight = 32.sp,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Start,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                        QuizType.ORDER -> {
                            Text(
                                text = item.promptText,
                                style = MaterialTheme.typography.headlineMedium,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                            Spacer(Modifier.height(4.dp))
                            Text(
                                text = "Susun blok perkataan Mandarin di bawah",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        QuizType.MCQ -> {
                            Text(
                                text = item.hanzi,
                                fontSize = 36.sp,
                                lineHeight = 44.sp,
                                fontWeight = FontWeight.Black,
                                color = MaterialTheme.colorScheme.onSurface,
                                textAlign = TextAlign.Center
                            )
                            if (showPinyin && item.pinyin.isNotBlank()) {
                                Spacer(Modifier.height(4.dp))
                                Text(
                                    text = item.pinyin,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = IndigoMid,
                                    textAlign = TextAlign.Center
                                )
                            }
                        }
                    }
                }
            }

            // Comprehension Question Line
            if (item.type == QuizType.COMPREHENSION && item.question.isNotBlank()) {
                Text(
                    text = item.question,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
            }

            // MCQ / Comprehension Options (2x2 Grid)
            if (item.type == QuizType.MCQ || item.type == QuizType.COMPREHENSION) {
                val rows = item.options.chunked(2)
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    rows.forEachIndexed { rIdx, pair ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            pair.forEachIndexed { cIdx, opt ->
                                val isSelected = arena.selectedOption == opt
                                val isCorrectOpt = opt == item.answer
                                val bg = when {
                                    arena.isAnswered && isCorrectOpt -> maz.jadeSoft
                                    arena.isAnswered && isSelected && !isCorrectOpt -> maz.coralSoft
                                    else -> MaterialTheme.colorScheme.surface
                                }
                                val border = when {
                                    arena.isAnswered && isCorrectOpt -> maz.jade
                                    arena.isAnswered && isSelected && !isCorrectOpt -> maz.coral
                                    else -> maz.cardBorder
                                }
                                val txtColor = when {
                                    arena.isAnswered && isCorrectOpt -> maz.onJadeSoft
                                    arena.isAnswered && isSelected && !isCorrectOpt -> maz.onCoralSoft
                                    else -> MaterialTheme.colorScheme.onSurface
                                }

                                TactileCard(
                                    modifier = Modifier
                                        .weight(1f)
                                        .testTag("quiz_opt_${rIdx}_${cIdx}"),
                                    containerColor = bg,
                                    borderColor = border,
                                    shadowColor = border,
                                    cornerRadius = 15.dp,
                                    depth = 3.dp,
                                    onClick = if (!arena.isAnswered) {
                                        {
                                            haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                            onSelectMcq(opt)
                                        }
                                    } else null
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .heightIn(min = 58.dp)
                                            .padding(horizontal = 10.dp, vertical = 12.dp),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Text(
                                            text = opt,
                                            style = MaterialTheme.typography.bodyMedium,
                                            fontWeight = FontWeight.Bold,
                                            color = txtColor,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Sentence Order Builder
            if (item.type == QuizType.ORDER) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 64.dp),
                    shape = RoundedCornerShape(16.dp),
                    color = maz.subtleSurface,
                    border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary.copy(alpha = 0.4f))
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        if (arena.selectedOrderWords.isEmpty()) {
                            Text(
                                text = "Ketik blok perkataan di bawah mengikut urutan",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        } else {
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                arena.selectedOrderWords.forEachIndexed { idx, w ->
                                    Surface(
                                        modifier = Modifier
                                            .heightIn(min = 44.dp)
                                            .clip(RoundedCornerShape(12.dp))
                                            .clickable(enabled = !arena.isAnswered) {
                                                onToggleOrderWord(w, idx)
                                            },
                                        shape = RoundedCornerShape(12.dp),
                                        color = maz.indigoSoft,
                                        border = BorderStroke(1.5.dp, MaterialTheme.colorScheme.primary)
                                    ) {
                                        Box(
                                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 9.dp),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Text(
                                                text = w,
                                                fontSize = 18.sp,
                                                fontWeight = FontWeight.Bold,
                                                color = maz.onIndigoSoft
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                // Word Bank
                FlowRow(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterHorizontally),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    item.words.forEachIndexed { idx, w ->
                        val isUsed = w in arena.selectedOrderWords
                        Surface(
                            modifier = Modifier
                                .heightIn(min = 48.dp)
                                .clip(RoundedCornerShape(13.dp))
                                .testTag("word_chip_$idx")
                                .clickable(enabled = !arena.isAnswered && !isUsed) {
                                    haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                                    onToggleOrderWord(w, null)
                                },
                            shape = RoundedCornerShape(13.dp),
                            color = if (isUsed) maz.cardBorder.copy(alpha = 0.45f) else MaterialTheme.colorScheme.surface,
                            border = BorderStroke(1.5.dp, maz.cardBorder)
                        ) {
                            Box(
                                modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = w,
                                    fontSize = 18.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = if (isUsed) 0.35f else 1f)
                                )
                            }
                        }
                    }
                }

                if (!arena.isAnswered) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        TactileSecondaryButton(
                            text = "Padam",
                            leadingIcon = Icons.Rounded.Backspace,
                            enabled = arena.selectedOrderWords.isNotEmpty(),
                            onClick = onClearOrderWords,
                            modifier = Modifier.weight(0.65f)
                        )
                        TactilePrimaryButton(
                            text = "Semak Ayat ✓",
                            enabled = arena.selectedOrderWords.isNotEmpty(),
                            onClick = onCheckOrder,
                            modifier = Modifier
                                .weight(1.35f)
                                .testTag("check_order_btn")
                        )
                    }
                }
            }

            // Animated Feedback Banner
            AnimatedVisibility(
                visible = arena.isAnswered,
                enter = fadeIn() + expandVertically()
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        color = if (arena.isAnswerCorrect) maz.jadeSoft else maz.coralSoft,
                        border = BorderStroke(
                            1.8.dp,
                            if (arena.isAnswerCorrect) maz.jade else maz.coral
                        )
                    ) {
                        Row(
                            modifier = Modifier.padding(14.dp),
                            verticalAlignment = Alignment.Top,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Icon(
                                imageVector = if (arena.isAnswerCorrect) Icons.Rounded.CheckCircle else Icons.Rounded.Close,
                                contentDescription = null,
                                tint = if (arena.isAnswerCorrect) maz.jade else maz.coral,
                                modifier = Modifier.size(22.dp)
                            )
                            Column {
                                Text(
                                    text = if (arena.isAnswerCorrect) "🎉 Yay! Jawapan Betul!" else "💡 Kurang tepat! Lihat pembetulan:",
                                    style = MaterialTheme.typography.titleSmall,
                                    color = if (arena.isAnswerCorrect) maz.onJadeSoft else maz.onCoralSoft
                                )
                                Spacer(Modifier.height(3.dp))
                                Text(
                                    text = arena.feedbackDetail,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = if (arena.isAnswerCorrect) maz.onJadeSoft else maz.onCoralSoft
                                )
                            }
                        }
                    }

                    TactilePrimaryButton(
                        text = if (arena.itemIndex < totalQuestions - 1) "Cabaran Seterusnya →" else "Tamatkan Stesen & Kumpul ⭐",
                        onClick = onNextChallenge,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("next_challenge_btn")
                    )
                }
            }

            // Mandarin Audio Footer
            if (item.speech.isNotBlank()) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AudioChipButton(
                        label = "Dengar Mandarin",
                        onClick = onSpeakNormal,
                        modifier = Modifier.testTag("quiz_speak_btn")
                    )
                    AudioChipButton(
                        label = "0.7x",
                        onClick = onSpeakSlow
                    )
                }
            }
        }
    }
}
