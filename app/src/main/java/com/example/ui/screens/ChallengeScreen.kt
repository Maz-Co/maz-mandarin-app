package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material.icons.rounded.Timer
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.FlashcardItem
import com.example.data.model.ToneExample
import com.example.ui.components.AudioChipButton
import com.example.ui.components.TactileCard
import com.example.ui.components.TactilePrimaryButton
import com.example.ui.components.TactileSecondaryButton
import com.example.ui.theme.AmberBright
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CoralCrimson
import com.example.ui.theme.CoralDark
import com.example.ui.theme.IndigoMid
import com.example.ui.theme.JadeImperial
import com.example.ui.theme.LocalMazColors
import com.example.ui.theme.NavyDeep
import com.example.ui.viewmodel.MazUiState
import com.example.ui.viewmodel.SpeedDrillUiState

@Composable
fun ChallengeScreen(
    uiState: MazUiState,
    isWideScreen: Boolean,
    onStartSpeedDrill: () -> Unit,
    onAnswerSpeedDrill: (String) -> Unit,
    onCancelSpeedDrill: () -> Unit,
    onSpeak: (String, Boolean) -> Unit,
    onVocabQueryChange: (String) -> Unit
) {
    if (uiState.speedDrill.isActive) {
        BackHandler(onBack = onCancelSpeedDrill)
        SpeedDrillActiveStage(
            drill = uiState.speedDrill,
            showPinyin = uiState.prefs.showPinyin,
            bestScore = uiState.prefs.bestSpeedDrillScore,
            onAnswer = onAnswerSpeedDrill,
            onSpeak = { onSpeak(it, false) },
            onReplay = onStartSpeedDrill,
            onExit = onCancelSpeedDrill
        )
        return
    }

    val maz = LocalMazColors.current
    val horizontalPad = if (isWideScreen) 24.dp else 16.dp

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 920.dp)
                .padding(horizontal = horizontalPad),
            contentPadding = PaddingValues(top = 10.dp, bottom = 28.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Speed Drill Hero Card
            item {
                SpeedDrillHeroCard(
                    levelLabel = uiState.prefs.currentLevel.label,
                    bestScore = uiState.prefs.bestSpeedDrillScore,
                    onStart = onStartSpeedDrill
                )
            }

            // Interactive 4-Tone Pinyin Lab
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "🎧 Makmal 4 Nada Pinyin",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Text(
                            text = "Ketik untuk dengar",
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }

                    val tonePairs = uiState.toneExamples.chunked(2)
                    tonePairs.forEach { pair ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            pair.forEach { tone ->
                                ToneLabCard(
                                    tone = tone,
                                    onClick = { onSpeak(tone.speech, false) },
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }
            }

            // Smart BCK Dictionary Search
            item {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "📖 Kamus Pintar BCK",
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                        Surface(shape = CircleShape, color = maz.indigoSoft) {
                            Text(
                                text = "${uiState.vocabResults.size} perkataan",
                                style = MaterialTheme.typography.labelSmall,
                                color = maz.onIndigoSoft,
                                modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                            )
                        }
                    }

                    OutlinedTextField(
                        value = uiState.vocabQuery,
                        onValueChange = onVocabQueryChange,
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("vocab_search_input"),
                        placeholder = { Text("Cari Hanzi, Pinyin atau maksud BM…") },
                        leadingIcon = { Icon(Icons.Rounded.Search, contentDescription = null) },
                        trailingIcon = {
                            if (uiState.vocabQuery.isNotEmpty()) {
                                IconButton(onClick = { onVocabQueryChange("") }) {
                                    Icon(Icons.Rounded.Close, contentDescription = "Padam carian")
                                }
                            }
                        },
                        singleLine = true,
                        shape = RoundedCornerShape(16.dp)
                    )
                }
            }

            // Vocab List Items
            if (uiState.vocabResults.isEmpty()) {
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        color = MaterialTheme.colorScheme.surface,
                        border = BorderStroke(1.dp, maz.cardBorder)
                    ) {
                        Column(
                            modifier = Modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(text = "🔍", fontSize = 32.sp)
                            Spacer(Modifier.height(6.dp))
                            Text(
                                text = "Tiada perkataan ditemui untuk \"${uiState.vocabQuery}\"",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurfaceVariant,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            } else {
                items(uiState.vocabResults.take(35)) { card ->
                    VocabDictionaryCard(
                        card = card,
                        onSpeak = { onSpeak(card.hanzi, false) }
                    )
                }
            }
        }
    }
}

@Composable
private fun SpeedDrillHeroCard(
    levelLabel: String,
    bestScore: Int,
    onStart: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(24.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(CoralDark, CoralCrimson, Color(0xFFBE185D))
                )
            )
            .padding(20.dp)
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(shape = CircleShape, color = Color.White.copy(alpha = 0.2f)) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(Icons.Rounded.Bolt, contentDescription = null, tint = AmberBright, modifier = Modifier.size(15.dp))
                        Text(
                            text = "LATIH TUBI KILAT · ${levelLabel.uppercase()}",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    }
                }

                if (bestScore > 0) {
                    Surface(shape = CircleShape, color = AmberGold) {
                        Row(
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(Icons.Rounded.EmojiEvents, contentDescription = null, tint = NavyDeep, modifier = Modifier.size(14.dp))
                            Text(
                                text = "Rekod: $bestScore",
                                style = MaterialTheme.typography.labelSmall,
                                color = NavyDeep
                            )
                        }
                    }
                }
            }

            Text(
                text = "Cabaran Kilat 10 Soalan ⚡",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White
            )
            Text(
                text = "Uji kepantasan dengan soalan rawak daripada semua 7 bab $levelLabel dalam 90 saat. Jawab berturut-turut untuk bonus kombo!",
                style = MaterialTheme.typography.bodySmall,
                color = Color.White.copy(alpha = 0.9f)
            )

            Spacer(Modifier.height(2.dp))

            TactilePrimaryButton(
                text = "Mula Cabaran Kilat Sekarang →",
                leadingIcon = Icons.Rounded.Bolt,
                gradientColors = listOf(AmberBright, AmberGold),
                shadowColor = Color(0xFF92400E),
                onClick = onStart,
                modifier = Modifier.testTag("start_speed_drill_btn")
            )
        }
    }
}

@Composable
private fun ToneLabCard(
    tone: ToneExample,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val maz = LocalMazColors.current
    val accent = when (tone.toneNumber) {
        1 -> IndigoMid
        2 -> JadeImperial
        3 -> CoralCrimson
        else -> AmberGold
    }

    TactileCard(
        modifier = modifier.testTag("tone_lab_card_${tone.toneNumber}"),
        cornerRadius = 18.dp,
        depth = 3.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(shape = RoundedCornerShape(8.dp), color = accent.copy(alpha = 0.14f)) {
                    Text(
                        text = "Nada ${tone.toneNumber}",
                        style = MaterialTheme.typography.labelSmall,
                        color = accent,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                    )
                }
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.VolumeUp,
                    contentDescription = "Dengar ${tone.pinyin}",
                    tint = accent,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Visual Pitch Contour Canvas
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(34.dp)
            ) {
                val w = size.width
                val h = size.height
                val path = Path()
                when (tone.toneNumber) {
                    1 -> {
                        path.moveTo(w * 0.08f, h * 0.25f)
                        path.lineTo(w * 0.92f, h * 0.25f)
                    }
                    2 -> {
                        path.moveTo(w * 0.08f, h * 0.78f)
                        path.quadraticTo(w * 0.5f, h * 0.55f, w * 0.92f, h * 0.2f)
                    }
                    3 -> {
                        path.moveTo(w * 0.08f, h * 0.55f)
                        path.quadraticTo(w * 0.42f, h * 0.95f, w * 0.92f, h * 0.35f)
                    }
                    else -> {
                        path.moveTo(w * 0.08f, h * 0.18f)
                        path.lineTo(w * 0.92f, h * 0.85f)
                    }
                }
                drawLine(
                    color = maz.cardBorder,
                    start = Offset(0f, h * 0.92f),
                    end = Offset(w, h * 0.92f),
                    strokeWidth = 2f
                )
                drawPath(
                    path = path,
                    color = accent,
                    style = Stroke(width = 7f, cap = StrokeCap.Round)
                )
            }

            Row(
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = tone.hanzi,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Column {
                    Text(
                        text = tone.pinyin,
                        style = MaterialTheme.typography.titleMedium,
                        color = accent
                    )
                    Text(
                        text = tone.meaningBm,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
            Text(
                text = tone.contourDesc,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun VocabDictionaryCard(
    card: FlashcardItem,
    onSpeak: () -> Unit
) {
    val maz = LocalMazColors.current
    TactileCard(
        cornerRadius = 16.dp,
        depth = 3.dp,
        onClick = onSpeak
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(maz.subtleSurface),
                contentAlignment = Alignment.Center
            ) {
                Text(text = card.emoji, fontSize = 24.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = card.hanzi,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = card.pinyin,
                        style = MaterialTheme.typography.labelLarge,
                        color = CoralCrimson
                    )
                }
                Text(
                    text = card.meaning,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${card.levelId.shortLabel} · ${card.chapterNum} — ${card.exCn}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            IconButton(
                onClick = onSpeak,
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.VolumeUp,
                    contentDescription = "Dengar ${card.hanzi}",
                    tint = maz.jade
                )
            }
        }
    }
}

@Composable
private fun SpeedDrillActiveStage(
    drill: SpeedDrillUiState,
    showPinyin: Boolean,
    bestScore: Int,
    onAnswer: (String) -> Unit,
    onSpeak: (String) -> Unit,
    onReplay: () -> Unit,
    onExit: () -> Unit
) {
    val maz = LocalMazColors.current
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .widthIn(max = 700.dp)
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            if (drill.isFinished) {
                TactileCard(cornerRadius = 24.dp, depth = 5.dp) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Text(text = "🏆", fontSize = 56.sp)
                        Text(
                            text = "Cabaran Kilat Tamat!",
                            style = MaterialTheme.typography.headlineLarge
                        )
                        Text(
                            text = "Anda menjawab ${drill.correctCount} daripada ${drill.questions.size} soalan dengan tepat.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            textAlign = TextAlign.Center
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Surface(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                color = maz.indigoSoft
                            ) {
                                Column(
                                    modifier = Modifier.padding(14.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Markah Akhir", style = MaterialTheme.typography.labelSmall, color = maz.onIndigoSoft)
                                    Text("${drill.score}", style = MaterialTheme.typography.headlineLarge, color = maz.onIndigoSoft)
                                }
                            }
                            Surface(
                                modifier = Modifier.weight(1f),
                                shape = RoundedCornerShape(16.dp),
                                color = maz.amberSoft
                            ) {
                                Column(
                                    modifier = Modifier.padding(14.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text("Rekod Terbaik", style = MaterialTheme.typography.labelSmall, color = maz.onAmberSoft)
                                    Text("${maxOf(bestScore, drill.score)}", style = MaterialTheme.typography.headlineLarge, color = maz.onAmberSoft)
                                }
                            }
                        }
                        Spacer(Modifier.height(4.dp))
                        TactilePrimaryButton(text = "Main Semula ⚡", onClick = onReplay)
                        TactileSecondaryButton(text = "Kembali ke Arena Cabaran", onClick = onExit)
                    }
                }
            } else {
                val current = drill.questions.getOrNull(drill.currentIndex)
                if (current != null) {
                    val item = current.quizItem
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        TactileSecondaryButton(
                            text = "Tamat",
                            onClick = onExit,
                            modifier = Modifier.width(96.dp)
                        )
                        Surface(
                            shape = CircleShape,
                            color = if (drill.timeLeftSeconds <= 15) maz.coralSoft else maz.indigoSoft
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(
                                    Icons.Rounded.Timer,
                                    contentDescription = null,
                                    tint = if (drill.timeLeftSeconds <= 15) maz.coral else maz.onIndigoSoft,
                                    modifier = Modifier.size(16.dp)
                                )
                                Text(
                                    text = "${drill.timeLeftSeconds}s",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = if (drill.timeLeftSeconds <= 15) maz.coral else maz.onIndigoSoft
                                )
                            }
                        }
                        Surface(shape = CircleShape, color = maz.amberSoft) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(Icons.Rounded.LocalFireDepartment, contentDescription = null, tint = maz.amberDark, modifier = Modifier.size(15.dp))
                                Text(
                                    text = "${drill.score} pts",
                                    style = MaterialTheme.typography.labelLarge,
                                    color = maz.onAmberSoft
                                )
                            }
                        }
                    }

                    LinearProgressIndicator(
                        progress = { (drill.currentIndex + 1).toFloat() / drill.questions.size.coerceAtLeast(1) },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(CircleShape),
                        color = CoralCrimson,
                        trackColor = maz.cardBorder
                    )

                    TactileCard(cornerRadius = 24.dp, depth = 5.dp) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(18.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Surface(shape = CircleShape, color = maz.indigoSoft) {
                                Text(
                                    text = "SOALAN ${drill.currentIndex + 1}/${drill.questions.size} · ${current.chapterTitle}",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = maz.onIndigoSoft,
                                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 5.dp)
                                )
                            }

                            Text(
                                text = item.instruction,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = TextAlign.Center
                            )

                            Surface(
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(18.dp),
                                color = maz.subtleSurface,
                                border = BorderStroke(1.5.dp, maz.cardBorder)
                            ) {
                                Column(
                                    modifier = Modifier.padding(18.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    if (item.emoji.isNotBlank()) {
                                        Text(text = item.emoji, fontSize = 38.sp)
                                    }
                                    Text(
                                        text = item.hanzi,
                                        fontSize = 32.sp,
                                        fontWeight = FontWeight.Black,
                                        textAlign = TextAlign.Center
                                    )
                                    if (showPinyin && item.pinyin.isNotBlank()) {
                                        Text(
                                            text = item.pinyin,
                                            style = MaterialTheme.typography.titleSmall,
                                            color = IndigoMid
                                        )
                                    }
                                }
                            }

                            val rows = item.options.chunked(2)
                            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                                rows.forEach { pair ->
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                                    ) {
                                        pair.forEach { opt ->
                                            val isSelected = drill.selectedOption == opt
                                            val isCorrect = opt == item.answer
                                            val bg = when {
                                                drill.isAnswered && isCorrect -> maz.jadeSoft
                                                drill.isAnswered && isSelected && !isCorrect -> maz.coralSoft
                                                else -> MaterialTheme.colorScheme.surface
                                            }
                                            val border = when {
                                                drill.isAnswered && isCorrect -> maz.jade
                                                drill.isAnswered && isSelected && !isCorrect -> maz.coral
                                                else -> maz.cardBorder
                                            }
                                            TactileCard(
                                                modifier = Modifier.weight(1f),
                                                containerColor = bg,
                                                borderColor = border,
                                                shadowColor = border,
                                                cornerRadius = 15.dp,
                                                depth = 3.dp,
                                                onClick = if (!drill.isAnswered) ({ onAnswer(opt) }) else null
                                            ) {
                                                Box(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .heightIn(min = 56.dp)
                                                        .padding(10.dp),
                                                    contentAlignment = Alignment.Center
                                                ) {
                                                    Text(
                                                        text = opt,
                                                        style = MaterialTheme.typography.bodyMedium,
                                                        fontWeight = FontWeight.Bold,
                                                        textAlign = TextAlign.Center
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                            if (item.speech.isNotBlank()) {
                                AudioChipButton(
                                    label = "Dengar Sebutan",
                                    onClick = { onSpeak(item.speech) }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
