package com.example.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Bolt
import androidx.compose.material.icons.outlined.Leaderboard
import androidx.compose.material.icons.outlined.School
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.rounded.Bolt
import androidx.compose.material.icons.rounded.Leaderboard
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.School
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.R
import com.example.data.model.AcademySubView
import com.example.data.model.MainTab
import com.example.ui.components.BadgeUnlockCelebrationOverlay
import com.example.ui.components.StatPillBadge
import com.example.ui.screens.AcademyScreen
import com.example.ui.screens.ArenaScreen
import com.example.ui.screens.ChallengeScreen
import com.example.ui.screens.ProgressScreen
import com.example.ui.screens.SettingsScreen
import com.example.ui.theme.CoralCrimson
import com.example.ui.theme.IndigoMid
import com.example.ui.theme.LocalMazColors
import com.example.ui.theme.MazMandarinTheme
import com.example.ui.theme.NavyDeep
import com.example.ui.viewmodel.MazUiState
import com.example.ui.viewmodel.MazViewModel

private data class NavDestination(
    val tab: MainTab,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val testTag: String
)

private val NAV_DESTINATIONS = listOf(
    NavDestination(MainTab.ACADEMY, "Akademi", Icons.Rounded.School, Icons.Outlined.School, "nav_academy"),
    NavDestination(MainTab.CHALLENGE, "Cabaran", Icons.Rounded.Bolt, Icons.Outlined.Bolt, "nav_challenge"),
    NavDestination(MainTab.PROGRESS, "Prestasi", Icons.Rounded.Leaderboard, Icons.Outlined.Leaderboard, "nav_progress"),
    NavDestination(MainTab.SETTINGS, "Tetapan", Icons.Rounded.Settings, Icons.Outlined.Settings, "nav_settings")
)

@Composable
fun MazMandarinApp(
    viewModel: MazViewModel = viewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MazMandarinTheme(darkTheme = uiState.prefs.darkTheme) {
        BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
            val isWideScreen = maxWidth >= 700.dp
            val isInFullscreenArena = uiState.activeTab == MainTab.ACADEMY &&
                uiState.academySubView == AcademySubView.ARENA
            val isInSpeedDrill = uiState.activeTab == MainTab.CHALLENGE && uiState.speedDrill.isActive

            if (!isInFullscreenArena && !isInSpeedDrill && uiState.activeTab != MainTab.ACADEMY) {
                BackHandler { viewModel.selectMainTab(MainTab.ACADEMY) }
            }

            val bgBase = MaterialTheme.colorScheme.background
            val maz = LocalMazColors.current
            val glowIndigo = if (maz.isDark) Color(0xFF312E81).copy(alpha = 0.25f) else Color(0xFFE0E7FF)
            val glowCoral = if (maz.isDark) Color(0xFF881337).copy(alpha = 0.2f) else Color(0xFFFFE4E6)

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(bgBase)
                    .drawBehind {
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(glowIndigo, Color.Transparent),
                                center = Offset(size.width * 0.12f, 0f),
                                radius = size.width * 0.65f
                            ),
                            radius = size.width * 0.65f,
                            center = Offset(size.width * 0.12f, 0f)
                        )
                        drawCircle(
                            brush = Brush.radialGradient(
                                colors = listOf(glowCoral, Color.Transparent),
                                center = Offset(size.width * 0.92f, size.height * 0.08f),
                                radius = size.width * 0.55f
                            ),
                            radius = size.width * 0.55f,
                            center = Offset(size.width * 0.92f, size.height * 0.08f)
                        )
                    }
            ) {
                if (isWideScreen) {
                    // Tablet & Foldable Canonical Layout with NavigationRail
                    Row(modifier = Modifier.fillMaxSize()) {
                        if (!isInFullscreenArena && !isInSpeedDrill) {
                            MazTabletNavigationRail(
                                uiState = uiState,
                                onSelectTab = viewModel::selectMainTab
                            )
                        }
                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxHeight()
                                .statusBarsPadding()
                        ) {
                            if (!isInFullscreenArena && !isInSpeedDrill) {
                                MazTopHeaderBar(
                                    uiState = uiState,
                                    onBrandClick = { viewModel.selectMainTab(MainTab.ACADEMY) },
                                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                                )
                            }
                            Box(modifier = Modifier.weight(1f)) {
                                MazMainContent(
                                    uiState = uiState,
                                    isWideScreen = true,
                                    viewModel = viewModel
                                )
                            }
                        }
                    }
                } else {
                    // Compact Phone Layout with M3 NavigationBar respecting WindowInsets.navigationBars
                    Scaffold(
                        containerColor = Color.Transparent,
                        contentWindowInsets = WindowInsets(0, 0, 0, 0),
                        topBar = {
                            if (!isInFullscreenArena && !isInSpeedDrill) {
                                MazTopHeaderBar(
                                    uiState = uiState,
                                    onBrandClick = { viewModel.selectMainTab(MainTab.ACADEMY) },
                                    modifier = Modifier
                                        .statusBarsPadding()
                                        .padding(horizontal = 16.dp, vertical = 10.dp)
                                )
                            }
                        },
                        bottomBar = {
                            if (!isInFullscreenArena && !isInSpeedDrill) {
                                MazBottomNavigationBar(
                                    activeTab = uiState.activeTab,
                                    onSelectTab = viewModel::selectMainTab
                                )
                            }
                        }
                    ) { innerPadding ->
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                                .then(
                                    if (isInFullscreenArena || isInSpeedDrill) {
                                        Modifier.statusBarsPadding()
                                    } else Modifier
                                )
                        ) {
                            MazMainContent(
                                uiState = uiState,
                                isWideScreen = false,
                                viewModel = viewModel
                            )
                        }
                    }
                }

                // Live Floating Achievement Badge Unlock Celebration Overlay
                BadgeUnlockCelebrationOverlay(
                    badge = uiState.pendingBadgeUnlocks.firstOrNull(),
                    remainingCount = (uiState.pendingBadgeUnlocks.size - 1).coerceAtLeast(0),
                    onViewTrophies = viewModel::viewTrophiesFromCelebration,
                    onDismiss = viewModel::dismissTopBadgeUnlock,
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .statusBarsPadding()
                        .widthIn(max = 560.dp)
                )
            }
        }
    }
}

@Composable
private fun MazMainContent(
    uiState: MazUiState,
    isWideScreen: Boolean,
    viewModel: MazViewModel
) {
    when {
        uiState.activeTab == MainTab.ACADEMY && uiState.academySubView == AcademySubView.ARENA -> {
            ArenaScreen(
                uiState = uiState,
                isTabletTwoPane = isWideScreen,
                onBack = { viewModel.navigateBackFromArena(isWideScreen) },
                onSpeak = viewModel::speakMandarin,
                onFlipCard = viewModel::toggleFlashcardFlip,
                onPrevCard = viewModel::previousFlashcard,
                onNextCard = viewModel::nextFlashcard,
                onSelectMcq = viewModel::submitMcqAnswer,
                onToggleOrderWord = viewModel::toggleOrderWord,
                onClearOrderWords = viewModel::clearOrderWords,
                onCheckOrder = viewModel::checkSentenceOrder,
                onNextChallenge = viewModel::nextQuizChallenge,
                onCelebrationAction = { openNext ->
                    viewModel.dismissCelebrationAndProceed(openNext, isWideScreen)
                }
            )
        }
        uiState.activeTab == MainTab.ACADEMY -> {
            AcademyScreen(
                uiState = uiState,
                isTabletTwoPane = isWideScreen,
                onSelectLevel = viewModel::selectLevel,
                onResumeLast = viewModel::resumeLastActivity,
                onOpenBadges = { viewModel.selectMainTab(MainTab.PROGRESS) },
                onOpenChapter = viewModel::openChapterStations,
                onPreviewChapter = viewModel::selectChapterForPreview,
                onBackToChapters = viewModel::navigateBackFromStations,
                onOpenStation = { stationId ->
                    viewModel.openStation(
                        levelId = uiState.prefs.currentLevel,
                        chapterId = uiState.selectedChapterId,
                        stationId = stationId,
                        startIndex = 0
                    )
                }
            )
        }
        uiState.activeTab == MainTab.CHALLENGE -> {
            ChallengeScreen(
                uiState = uiState,
                isWideScreen = isWideScreen,
                onStartSpeedDrill = viewModel::startSpeedDrill,
                onAnswerSpeedDrill = viewModel::answerSpeedDrill,
                onCancelSpeedDrill = viewModel::cancelSpeedDrill,
                onSpeak = viewModel::speakMandarin,
                onVocabQueryChange = viewModel::updateVocabQuery
            )
        }
        uiState.activeTab == MainTab.PROGRESS -> {
            ProgressScreen(
                uiState = uiState,
                isWideScreen = isWideScreen,
                buildPlainTextReport = viewModel::buildPlainTextReport,
                buildPrintableHtmlReport = viewModel::buildPrintableHtmlReport
            )
        }
        uiState.activeTab == MainTab.SETTINGS -> {
            SettingsScreen(
                uiState = uiState,
                onTogglePinyin = viewModel::setShowPinyin,
                onToggleSoundFx = viewModel::setSoundFx,
                onToggleAutoSpeak = viewModel::setAutoSpeak,
                onToggleDarkTheme = viewModel::setDarkTheme,
                onSelectSpeechRate = viewModel::setSpeechRate,
                onSelectVoice = viewModel::setPreferredVoice,
                onTestVoice = { viewModel.speakMandarin("你好！欢迎来到马兹华语学院。", slow = false) },
                onSaveProfile = viewModel::saveStudentProfile,
                onResetLevelProgress = viewModel::resetCurrentLevelProgress
            )
        }
    }
}

@Composable
private fun MazTopHeaderBar(
    uiState: MazUiState,
    onBrandClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val maz = LocalMazColors.current
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(18.dp),
        color = MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.5.dp, maz.cardBorder),
        shadowElevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(onClick = onBrandClick),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_maz_mandarin_foreground_1795482484183),
                    contentDescription = "Logo MAZ Mandarin",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(42.dp)
                        .clip(RoundedCornerShape(12.dp))
                )
                Column {
                    Text(
                        text = "MAZ MANDARIN",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Black,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "AKADEMIK · BCK KSSM",
                        style = MaterialTheme.typography.labelSmall,
                        color = CoralCrimson
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                if (uiState.streakDays > 0) {
                    StatPillBadge(
                        icon = Icons.Rounded.LocalFireDepartment,
                        value = "${uiState.streakDays}",
                        containerColor = maz.coralSoft,
                        contentColor = maz.onCoralSoft,
                        borderColor = maz.coral.copy(alpha = 0.4f)
                    )
                }
                StatPillBadge(
                    icon = Icons.Rounded.Star,
                    value = "${uiState.levelStars}",
                    containerColor = maz.amberSoft,
                    contentColor = maz.onAmberSoft,
                    borderColor = maz.amber.copy(alpha = 0.55f)
                )
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = if (maz.isDark) IndigoMid else NavyDeep
                ) {
                    Text(
                        text = uiState.prefs.currentLevel.shortLabel,
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.White,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun MazBottomNavigationBar(
    activeTab: MainTab,
    onSelectTab: (MainTab) -> Unit
) {
    val maz = LocalMazColors.current
    NavigationBar(
        containerColor = if (maz.isDark) Color(0xFF0F172A) else Color.White,
        tonalElevation = 4.dp,
        windowInsets = WindowInsets.navigationBars
    ) {
        NAV_DESTINATIONS.forEach { dest ->
            val selected = activeTab == dest.tab
            NavigationBarItem(
                selected = selected,
                onClick = { onSelectTab(dest.tab) },
                modifier = Modifier.testTag(dest.testTag),
                icon = {
                    Icon(
                        imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                        contentDescription = dest.label
                    )
                },
                label = {
                    Text(
                        text = dest.label,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = if (selected) FontWeight.ExtraBold else FontWeight.SemiBold
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = IndigoMid,
                    selectedTextColor = IndigoMid,
                    indicatorColor = maz.indigoSoft
                )
            )
        }
    }
}

@Composable
private fun MazTabletNavigationRail(
    uiState: MazUiState,
    onSelectTab: (MainTab) -> Unit
) {
    val maz = LocalMazColors.current
    NavigationRail(
        containerColor = if (maz.isDark) Color(0xFF0F172A) else Color.White,
        modifier = Modifier
            .fillMaxHeight()
            .width(88.dp),
        header = {
            Column(
                modifier = Modifier
                    .statusBarsPadding()
                    .padding(top = 12.dp, bottom = 12.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_maz_mandarin_foreground_1795482484183),
                    contentDescription = "MAZ Mandarin",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                )
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "MAZ",
                    style = MaterialTheme.typography.labelSmall,
                    color = CoralCrimson,
                    fontWeight = FontWeight.Black
                )
            }
        }
    ) {
        Spacer(Modifier.height(8.dp))
        NAV_DESTINATIONS.forEach { dest ->
            val selected = uiState.activeTab == dest.tab
            NavigationRailItem(
                selected = selected,
                onClick = { onSelectTab(dest.tab) },
                modifier = Modifier.testTag(dest.testTag),
                icon = {
                    Icon(
                        imageVector = if (selected) dest.selectedIcon else dest.unselectedIcon,
                        contentDescription = dest.label
                    )
                },
                label = {
                    Text(
                        text = dest.label,
                        style = MaterialTheme.typography.labelSmall
                    )
                },
                colors = NavigationRailItemDefaults.colors(
                    selectedIconColor = IndigoMid,
                    selectedTextColor = IndigoMid,
                    indicatorColor = maz.indigoSoft
                )
            )
        }
    }
}
