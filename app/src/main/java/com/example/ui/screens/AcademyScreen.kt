package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AcademySubView
import com.example.data.model.ChapterMeta
import com.example.data.model.LevelId
import com.example.data.model.StationMeta
import com.example.ui.components.NextMilestoneNudgeCard
import com.example.ui.components.TactileCard
import com.example.ui.theme.AmberBright
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberGold
import com.example.ui.theme.CoralCrimson
import com.example.ui.theme.IndigoMid
import com.example.ui.theme.IndigoRoyal
import com.example.ui.theme.JadeImperial
import com.example.ui.theme.LocalMazColors
import com.example.ui.theme.NavyDeep
import com.example.ui.theme.NavySurface
import com.example.ui.viewmodel.MazUiState

@Composable
fun AcademyScreen(
    uiState: MazUiState,
    isTabletTwoPane: Boolean,
    onSelectLevel: (LevelId) -> Unit,
    onResumeLast: () -> Unit,
    onOpenBadges: () -> Unit,
    onOpenChapter: (String) -> Unit,
    onPreviewChapter: (String) -> Unit,
    onBackToChapters: () -> Unit,
    onOpenStation: (String) -> Unit
) {
    val activeChapter = uiState.chapters.firstOrNull { it.id == uiState.selectedChapterId }
        ?: uiState.chapters.firstOrNull()

    if (!isTabletTwoPane && uiState.academySubView == AcademySubView.STATIONS) {
        BackHandler(onBack = onBackToChapters)
        if (activeChapter != null) {
            StationsRoadmapPane(
                uiState = uiState,
                chapter = activeChapter,
                showBackButton = true,
                onBack = onBackToChapters,
                onOpenStation = onOpenStation,
                modifier = Modifier.fillMaxSize()
            )
        }
        return
    }

    if (isTabletTwoPane) {
        // Canonical Tablet List-Detail Layout: Chapters on left, 7-Station Roadmap live on right
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            ChaptersCatalogPane(
                uiState = uiState,
                isTabletTwoPane = true,
                onSelectLevel = onSelectLevel,
                onResumeLast = onResumeLast,
                onOpenBadges = onOpenBadges,
                onChapterClick = { onPreviewChapter(it) },
                modifier = Modifier
                    .weight(1.08f)
                    .fillMaxHeight()
            )
            if (activeChapter != null) {
                StationsRoadmapPane(
                    uiState = uiState,
                    chapter = activeChapter,
                    showBackButton = false,
                    onBack = {},
                    onOpenStation = onOpenStation,
                    modifier = Modifier
                        .weight(0.92f)
                        .fillMaxHeight()
                )
            }
        }
    } else {
        ChaptersCatalogPane(
            uiState = uiState,
            isTabletTwoPane = false,
            onSelectLevel = onSelectLevel,
            onResumeLast = onResumeLast,
            onOpenBadges = onOpenBadges,
            onChapterClick = { onOpenChapter(it) },
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        )
    }
}

@Composable
private fun ChaptersCatalogPane(
    uiState: MazUiState,
    isTabletTwoPane: Boolean,
    onSelectLevel: (LevelId) -> Unit,
    onResumeLast: () -> Unit,
    onOpenBadges: () -> Unit,
    onChapterClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val maz = LocalMazColors.current
    val level = uiState.prefs.currentLevel

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(top = 8.dp, bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Segmented Form Switcher (T1 / T2 / T3)
        item {
            LevelSegmentedBar(
                selectedLevel = level,
                onSelectLevel = onSelectLevel
            )
        }

        // Daily XP Goal & Rank Strip
        item {
            DailyMissionCard(uiState = uiState)
        }

        // Next Milestone Motivational Nudge
        uiState.nextClosestBadge?.let { nextBadge ->
            item {
                NextMilestoneNudgeCard(
                    nextBadge = nextBadge,
                    unlockedCount = uiState.badges.count { it.isUnlocked },
                    totalCount = uiState.badges.size,
                    onClick = onOpenBadges
                )
            }
        }

        // Resume Last Activity Hero 3D Banner
        item {
            ResumeHeroBanner(
                uiState = uiState,
                onResumeClick = onResumeLast
            )
        }

        // Section Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Modul Pembelajaran (7 Bab)",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Surface(
                    shape = CircleShape,
                    color = maz.indigoSoft
                ) {
                    Text(
                        text = "${uiState.levelCompletedCount}/49 Stesen",
                        style = MaterialTheme.typography.labelMedium,
                        color = maz.onIndigoSoft,
                        modifier = Modifier.padding(horizontal = 11.dp, vertical = 5.dp)
                    )
                }
            }
        }

        // 2-column Chapter Grid (first 6 chapters in 3 rows of 2, chapter 7 spans full width!)
        val chapters = uiState.chapters
        val pairs = chapters.take(6).chunked(2)
        items(pairs) { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                rowItems.forEach { chap ->
                    val done = countCompletedStations(uiState, level, chap.id)
                    ChapterTile3D(
                        chapter = chap,
                        doneCount = done,
                        isSelectedOnTablet = isTabletTwoPane && uiState.selectedChapterId == chap.id,
                        onClick = { onChapterClick(chap.id) },
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }

        chapters.getOrNull(6)?.let { seventh ->
            item {
                val done = countCompletedStations(uiState, level, seventh.id)
                ChapterTile3D(
                    chapter = seventh,
                    doneCount = done,
                    isSelectedOnTablet = isTabletTwoPane && uiState.selectedChapterId == seventh.id,
                    onClick = { onChapterClick(seventh.id) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
private fun LevelSegmentedBar(
    selectedLevel: LevelId,
    onSelectLevel: (LevelId) -> Unit
) {
    val maz = LocalMazColors.current
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = if (maz.isDark) NavySurface else Color(0xFFE2E8F0)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp),
            horizontalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            LevelId.entries.forEach { lvl ->
                val isSelected = lvl == selectedLevel
                Surface(
                    modifier = Modifier
                        .weight(1f)
                        .heightIn(min = 48.dp)
                        .clip(RoundedCornerShape(13.dp))
                        .testTag("level_tab_${lvl.key}")
                        .clickable { onSelectLevel(lvl) },
                    shape = RoundedCornerShape(13.dp),
                    color = if (isSelected) MaterialTheme.colorScheme.surface else Color.Transparent,
                    shadowElevation = if (isSelected) 2.dp else 0.dp
                ) {
                    Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(horizontal = 4.dp)) {
                        Text(
                            text = lvl.label,
                            style = MaterialTheme.typography.labelLarge,
                            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Bold,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DailyMissionCard(uiState: MazUiState) {
    val maz = LocalMazColors.current
    val progress = (uiState.todayXp.toFloat() / uiState.dailyGoalXp.toFloat()).coerceIn(0f, 1f)
    val achieved = uiState.todayXp >= uiState.dailyGoalXp

    TactileCard(
        cornerRadius = 18.dp,
        depth = 3.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(13.dp))
                    .background(if (achieved) maz.jadeSoft else maz.amberSoft),
                contentAlignment = Alignment.Center
            ) {
                Text(text = uiState.rank.emoji, fontSize = 22.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${uiState.rank.titleBm} · ${uiState.rank.titleCn}",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "${uiState.todayXp}/${uiState.dailyGoalXp} XP hari ini",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (achieved) maz.jade else MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(Modifier.height(6.dp))
                LinearProgressIndicator(
                    progress = { progress },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(8.dp)
                        .clip(CircleShape),
                    color = if (achieved) maz.jade else IndigoMid,
                    trackColor = maz.cardBorder
                )
            }
        }
    }
}

@Composable
private fun ResumeHeroBanner(
    uiState: MazUiState,
    onResumeClick: () -> Unit
) {
    val prefs = uiState.prefs
    val hasLast = prefs.lastChapterId.isNotBlank() && prefs.lastStationId.isNotBlank()
    val displayLevel = if (hasLast) LevelId.fromKey(prefs.lastLevelKey) else prefs.currentLevel
    val chapList = uiState.chapters
    val activeChap = if (hasLast && displayLevel == prefs.currentLevel) {
        chapList.firstOrNull { it.id == prefs.lastChapterId } ?: chapList.firstOrNull()
    } else {
        chapList.firstOrNull()
    }
    val station = if (hasLast) {
        uiState.stationsMeta.firstOrNull { it.id == prefs.lastStationId } ?: uiState.stationsMeta.first()
    } else {
        uiState.stationsMeta.first()
    }

    val titleText = if (hasLast && activeChap != null) {
        "${displayLevel.label} · ${activeChap.numLabel}: ${activeChap.title}"
    } else if (activeChap != null) {
        "${displayLevel.label} · ${activeChap.numLabel}: ${activeChap.title}"
    } else {
        "Mula Belajar Bahasa Cina BCK"
    }

    val subText = if (hasLast) {
        "Terakhir di: ${station.icon} ${station.title}"
    } else {
        "Mula dengan Stesen 1: Kad Imbas Pintar 3D"
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 5.dp)
    ) {
        // Dark 3D lip
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(top = 5.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xFF070C17))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(NavyDeep, NavySurface, IndigoRoyal)
                    )
                )
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                        Icon(
                            imageVector = Icons.Rounded.LocalFireDepartment,
                            contentDescription = null,
                            tint = AmberBright,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "SAMBUNG LATIHAN TERAKHIR",
                            style = MaterialTheme.typography.labelSmall,
                            color = AmberBright
                        )
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = titleText,
                        style = MaterialTheme.typography.titleMedium,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(2.dp))
                    Text(
                        text = subText,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFFCBD5E1),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                // Golden 3D Action Pill
                Surface(
                    modifier = Modifier
                        .heightIn(min = 48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .testTag("resume_last_button")
                        .clickable(onClick = onResumeClick),
                    shape = RoundedCornerShape(14.dp),
                    color = AmberGold,
                    border = BorderStroke(1.dp, AmberBright)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (hasLast) "Teruskan" else "Mula",
                            style = MaterialTheme.typography.labelLarge,
                            color = Color(0xFF451A03),
                            fontWeight = FontWeight.Black
                        )
                        Icon(
                            imageVector = Icons.Rounded.PlayArrow,
                            contentDescription = null,
                            tint = Color(0xFF451A03),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ChapterTile3D(
    chapter: ChapterMeta,
    doneCount: Int,
    isSelectedOnTablet: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val maz = LocalMazColors.current
    val isDone = doneCount >= 7
    val pct = (doneCount / 7f).coerceIn(0f, 1f)

    val borderColor = when {
        isSelectedOnTablet -> MaterialTheme.colorScheme.primary
        isDone -> maz.jade.copy(alpha = 0.7f)
        else -> maz.cardBorder
    }
    val shadowColor = when {
        isSelectedOnTablet -> MaterialTheme.colorScheme.primary.copy(alpha = 0.45f)
        isDone -> maz.jade.copy(alpha = 0.4f)
        else -> maz.cardShadow3d
    }
    val bgColor = when {
        isDone -> maz.jadeSoft.copy(alpha = if (maz.isDark) 0.55f else 0.7f)
        isSelectedOnTablet -> maz.indigoSoft.copy(alpha = if (maz.isDark) 0.5f else 0.55f)
        else -> MaterialTheme.colorScheme.surface
    }

    TactileCard(
        modifier = modifier.testTag("chapter_tile_${chapter.id}"),
        containerColor = bgColor,
        borderColor = borderColor,
        shadowColor = shadowColor,
        cornerRadius = 18.dp,
        depth = 4.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    shape = RoundedCornerShape(8.dp),
                    color = if (isDone) maz.jadeSoft else maz.indigoSoft
                ) {
                    Text(
                        text = chapter.numLabel,
                        style = MaterialTheme.typography.labelSmall,
                        color = if (isDone) maz.onJadeSoft else maz.onIndigoSoft,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
                Text(text = chapter.emoji, fontSize = 24.sp)
            }

            Spacer(Modifier.height(10.dp))

            Text(
                text = chapter.title,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(Modifier.height(3.dp))
            Text(
                text = chapter.hanzi,
                style = MaterialTheme.typography.bodySmall,
                color = maz.jade,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                LinearProgressIndicator(
                    progress = { pct },
                    modifier = Modifier
                        .weight(1f)
                        .height(6.dp)
                        .clip(CircleShape),
                    color = if (isDone) maz.jade else IndigoMid,
                    trackColor = maz.cardBorder
                )
                Text(
                    text = "$doneCount/7",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

@Composable
private fun StationsRoadmapPane(
    uiState: MazUiState,
    chapter: ChapterMeta,
    showBackButton: Boolean,
    onBack: () -> Unit,
    onOpenStation: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val maz = LocalMazColors.current
    val level = uiState.prefs.currentLevel
    val doneCount = countCompletedStations(uiState, level, chapter.id)

    LazyColumn(
        modifier = modifier.padding(horizontal = if (showBackButton) 16.dp else 0.dp),
        contentPadding = PaddingValues(top = 8.dp, bottom = 28.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (showBackButton) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Surface(
                        modifier = Modifier
                            .heightIn(min = 48.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .testTag("back_to_chapters_btn")
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
                                contentDescription = "Kembali ke Senarai Bab",
                                tint = MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(Modifier.width(6.dp))
                            Text(
                                text = "Senarai Bab",
                                style = MaterialTheme.typography.labelLarge,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }

                    Surface(
                        shape = CircleShape,
                        color = maz.jadeSoft
                    ) {
                        Text(
                            text = "$doneCount/7 Selesai",
                            style = MaterialTheme.typography.labelMedium,
                            color = maz.onJadeSoft,
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                        )
                    }
                }
            }
        }

        // Chapter Spotlight Banner
        item {
            TactileCard(
                containerColor = maz.indigoSoft.copy(alpha = if (maz.isDark) 0.7f else 0.85f),
                borderColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.35f),
                shadowColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.25f),
                cornerRadius = 20.dp,
                depth = 4.dp
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${level.label.uppercase()} · ${chapter.numLabel.uppercase()}",
                            style = MaterialTheme.typography.labelSmall,
                            color = CoralCrimson
                        )
                        if (!showBackButton) {
                            Surface(shape = CircleShape, color = maz.jadeSoft) {
                                Text(
                                    text = "$doneCount/7 Selesai",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = maz.onJadeSoft,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                                )
                            }
                        }
                    }
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "${chapter.emoji} ${chapter.title}",
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = chapter.desc,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }

        // 7 Pedagogical Stations List
        items(uiState.stationsMeta, key = { it.id }) { st ->
            val key = "${level.key}_${chapter.id}_${st.id}"
            val progress = uiState.progressMap[key]
            StationNodeCard(
                station = st,
                isDone = progress != null,
                stars = progress?.stars ?: 0,
                onClick = { onOpenStation(st.id) }
            )
        }
    }
}

@Composable
private fun StationNodeCard(
    station: StationMeta,
    isDone: Boolean,
    stars: Int,
    onClick: () -> Unit
) {
    val maz = LocalMazColors.current
    val bgColor = when {
        isDone -> maz.jadeSoft.copy(alpha = if (maz.isDark) 0.55f else 0.75f)
        station.isFlashcard -> maz.coralSoft.copy(alpha = if (maz.isDark) 0.45f else 0.65f)
        else -> MaterialTheme.colorScheme.surface
    }
    val borderColor = when {
        isDone -> maz.jade.copy(alpha = 0.65f)
        station.isFlashcard -> maz.coral.copy(alpha = 0.55f)
        else -> maz.cardBorder
    }
    val shadowColor = when {
        isDone -> maz.jade.copy(alpha = 0.35f)
        station.isFlashcard -> maz.coral.copy(alpha = 0.3f)
        else -> maz.cardShadow3d
    }

    TactileCard(
        modifier = Modifier
            .fillMaxWidth()
            .testTag("station_node_${station.id}"),
        containerColor = bgColor,
        borderColor = borderColor,
        shadowColor = shadowColor,
        cornerRadius = 18.dp,
        depth = 4.dp,
        onClick = onClick
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
                    .size(46.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        when {
                            isDone -> maz.jade.copy(alpha = 0.18f)
                            station.isFlashcard -> maz.coral.copy(alpha = 0.16f)
                            else -> maz.indigoSoft
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(text = station.icon, fontSize = 22.sp)
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = station.stepLabel.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = station.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(Modifier.height(2.dp))
                Text(
                    text = station.desc,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Surface(
                shape = RoundedCornerShape(11.dp),
                color = if (isDone) maz.jade else maz.indigoSoft
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    if (isDone) {
                        Icon(
                            imageVector = if (stars >= 3) Icons.Rounded.Star else Icons.Rounded.CheckCircle,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = "Ulang",
                            style = MaterialTheme.typography.labelSmall,
                            color = Color.White
                        )
                    } else {
                        Text(
                            text = "Mula →",
                            style = MaterialTheme.typography.labelSmall,
                            color = maz.onIndigoSoft
                        )
                    }
                }
            }
        }
    }
}

private fun countCompletedStations(uiState: MazUiState, level: LevelId, chapterId: String): Int {
    return uiState.stationsMeta.count { st ->
        uiState.progressMap.containsKey("${level.key}_${chapterId}_${st.id}")
    }
}
