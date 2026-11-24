package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoAwesome
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.ChevronRight
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.EmojiEvents
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.AchievementBadge
import com.example.data.model.BadgeCategory
import com.example.data.model.BadgeTier
import com.example.ui.theme.AmberBright
import com.example.ui.theme.AmberDark
import com.example.ui.theme.AmberGold
import com.example.ui.theme.IndigoBright
import com.example.ui.theme.IndigoMid
import com.example.ui.theme.JadeBright
import com.example.ui.theme.JadeDark
import com.example.ui.theme.JadeImperial
import com.example.ui.theme.LocalMazColors
import com.example.ui.theme.NavyDeep
import com.example.ui.theme.NavySurface
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private data class TierPalette(
    val gradient: List<Color>,
    val ringColor: Color,
    val pillBg: Color,
    val pillText: Color
)

@Composable
private fun tierPalette(tier: BadgeTier, isUnlocked: Boolean): TierPalette {
    val maz = LocalMazColors.current
    if (!isUnlocked) {
        return TierPalette(
            gradient = if (maz.isDark) listOf(Color(0xFF1E293B), Color(0xFF0F172A))
            else listOf(Color(0xFFE2E8F0), Color(0xFFCBD5E1)),
            ringColor = maz.cardBorder,
            pillBg = maz.subtleSurface,
            pillText = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
    return when (tier) {
        BadgeTier.BRONZE -> TierPalette(
            gradient = listOf(Color(0xFFF59E0B), Color(0xFFB45309)),
            ringColor = Color(0xFFD97706),
            pillBg = if (maz.isDark) Color(0xFF3E230B) else Color(0xFFFEF3C7),
            pillText = if (maz.isDark) Color(0xFFFCD34D) else Color(0xFF92400E)
        )
        BadgeTier.SILVER -> TierPalette(
            gradient = listOf(IndigoBright, IndigoMid),
            ringColor = IndigoMid,
            pillBg = maz.indigoSoft,
            pillText = maz.onIndigoSoft
        )
        BadgeTier.GOLD -> TierPalette(
            gradient = listOf(Color(0xFFFDE047), AmberGold, AmberDark),
            ringColor = AmberGold,
            pillBg = maz.amberSoft,
            pillText = maz.onAmberSoft
        )
        BadgeTier.JADE -> TierPalette(
            gradient = listOf(JadeBright, JadeImperial, JadeDark),
            ringColor = JadeImperial,
            pillBg = maz.jadeSoft,
            pillText = maz.onJadeSoft
        )
    }
}

/**
 * Layered metallic Chinese Seal Medal emblem displaying both the achievement emoji
 * and a stylized Hanzi motif stamp in the corner.
 */
@Composable
fun TierSealMedal(
    badge: AchievementBadge,
    size: Dp = 62.dp,
    modifier: Modifier = Modifier
) {
    val palette = tierPalette(badge.tier, badge.isUnlocked)
    val outerRingWidth = if (size >= 80.dp) 3.dp else 2.dp

    Box(
        modifier = modifier.size(size),
        contentAlignment = Alignment.Center
    ) {
        // Outer metallic ring + gradient coin
        Box(
            modifier = Modifier
                .size(size)
                .clip(CircleShape)
                .background(Brush.linearGradient(palette.gradient))
                .border(outerRingWidth, palette.ringColor.copy(alpha = 0.85f), CircleShape),
            contentAlignment = Alignment.Center
        ) {
            // Subtle inner dashed-feel halo
            Box(
                modifier = Modifier
                    .size(size * 0.84f)
                    .clip(CircleShape)
                    .border(1.dp, Color.White.copy(alpha = if (badge.isUnlocked) 0.45f else 0.2f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                if (badge.isUnlocked) {
                    Text(
                        text = badge.emoji,
                        fontSize = (size.value * 0.44f).sp
                    )
                } else {
                    Icon(
                        imageVector = Icons.Rounded.Lock,
                        contentDescription = "Terkunci",
                        tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.65f),
                        modifier = Modifier.size(size * 0.36f)
                    )
                }
            }
        }

        // Traditional Chinese Hanzi Stamp Pill at bottom-right
        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .size(size * 0.34f),
            shape = CircleShape,
            color = if (badge.isUnlocked) NavyDeep else MaterialTheme.colorScheme.surfaceVariant,
            border = BorderStroke(1.2.dp, if (badge.isUnlocked) AmberBright else palette.ringColor)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Text(
                    text = badge.hanziSeal,
                    fontSize = (size.value * 0.17f).sp,
                    fontWeight = FontWeight.Black,
                    color = if (badge.isUnlocked) AmberBright else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

/**
 * Interactive Trophy Gallery section with category filters, tier colors, live milestone
 * progress bars on locked badges, and collector detail inspection.
 */
@Composable
fun BadgesGallerySection(
    badges: List<AchievementBadge>,
    isWideScreen: Boolean,
    modifier: Modifier = Modifier
) {
    val maz = LocalMazColors.current
    var selectedCategory by remember { mutableStateOf<BadgeCategory?>(null) }
    var inspectedBadge by remember { mutableStateOf<AchievementBadge?>(null) }

    val unlockedCount = badges.count { it.isUnlocked }
    val earnedBadgeXp = badges.filter { it.isUnlocked }.sumOf { it.xpReward }
    val filteredBadges = remember(badges, selectedCategory) {
        val list = if (selectedCategory == null) badges else badges.filter { it.category == selectedCategory }
        list.sortedWith(compareByDescending<AchievementBadge> { it.isUnlocked }.thenByDescending { it.progressFraction })
    }

    inspectedBadge?.let { badge ->
        BadgeDetailDialog(
            badge = badge,
            onDismiss = { inspectedBadge = null }
        )
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // Section Header + Unlocked Ratio
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "🏅 Dewan Lencana Pencapaian",
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
                Text(
                    text = "+$earnedBadgeXp XP bonus lencana · Ketik lencana untuk butiran",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Surface(shape = CircleShape, color = maz.jadeSoft) {
                Text(
                    text = "$unlockedCount/${badges.size}",
                    style = MaterialTheme.typography.labelLarge,
                    color = maz.onJadeSoft,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp)
                )
            }
        }

        // Category Filter Chips
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            BadgeFilterPill(
                label = "Semua (${badges.size})",
                selected = selectedCategory == null,
                onClick = { selectedCategory = null },
                tag = "badge_filter_all"
            )
            BadgeCategory.entries.forEach { cat ->
                val count = badges.count { it.category == cat && it.isUnlocked }
                val total = badges.count { it.category == cat }
                BadgeFilterPill(
                    label = "${cat.iconEmoji} ${cat.labelBm} ($count/$total)",
                    selected = selectedCategory == cat,
                    onClick = { selectedCategory = cat },
                    tag = "badge_filter_${cat.name.lowercase()}"
                )
            }
        }

        // Responsive Grid (2 columns on compact, 3 columns on wide/tablet)
        val columns = if (isWideScreen) 3 else 2
        val rows = filteredBadges.chunked(columns)
        rows.forEach { rowBadges ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                rowBadges.forEach { badge ->
                    AchievementBadgeTile(
                        badge = badge,
                        onClick = { inspectedBadge = badge },
                        modifier = Modifier.weight(1f)
                    )
                }
                repeat(columns - rowBadges.size) {
                    Spacer(Modifier.weight(1f))
                }
            }
        }
    }
}

@Composable
private fun BadgeFilterPill(
    label: String,
    selected: Boolean,
    onClick: () -> Unit,
    tag: String
) {
    val maz = LocalMazColors.current
    Surface(
        modifier = Modifier
            .heightIn(min = 42.dp)
            .clip(CircleShape)
            .testTag(tag)
            .clickable(onClick = onClick),
        shape = CircleShape,
        color = if (selected) IndigoMid else MaterialTheme.colorScheme.surface,
        border = BorderStroke(1.2.dp, if (selected) IndigoMid else maz.cardBorder)
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = if (selected) Color.White else MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

@Composable
private fun AchievementBadgeTile(
    badge: AchievementBadge,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val maz = LocalMazColors.current
    val palette = tierPalette(badge.tier, badge.isUnlocked)

    TactileCard(
        modifier = modifier.testTag("achievement_badge_${badge.id}"),
        containerColor = if (badge.isUnlocked) MaterialTheme.colorScheme.surface else maz.subtleSurface,
        borderColor = if (badge.isUnlocked) palette.ringColor.copy(alpha = 0.7f) else maz.cardBorder,
        shadowColor = if (badge.isUnlocked) palette.ringColor.copy(alpha = 0.35f) else maz.cardShadow3d,
        cornerRadius = 18.dp,
        depth = 3.dp,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Tier Rarity Tag + Bonus XP
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(shape = RoundedCornerShape(7.dp), color = palette.pillBg) {
                    Text(
                        text = badge.tier.labelBm,
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 9.5.sp,
                        color = palette.pillText,
                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                    )
                }
                Text(
                    text = "+${badge.xpReward} XP",
                    style = MaterialTheme.typography.labelSmall,
                    fontSize = 10.sp,
                    color = if (badge.isUnlocked) maz.jade else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            TierSealMedal(badge = badge, size = 58.dp)

            Text(
                text = badge.title,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = badge.desc,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 11.sp,
                lineHeight = 14.sp,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            if (badge.isUnlocked) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.CheckCircle,
                        contentDescription = "Dibuka",
                        tint = maz.jade,
                        modifier = Modifier.size(14.dp)
                    )
                    Text(
                        text = "Berjaya Dibuka",
                        style = MaterialTheme.typography.labelSmall,
                        color = maz.jade
                    )
                }
            } else {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    LinearProgressIndicator(
                        progress = { badge.progressFraction },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(6.dp)
                            .clip(CircleShape),
                        color = IndigoMid,
                        trackColor = maz.cardBorder
                    )
                    Text(
                        text = badge.progressText,
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

/**
 * Rich collector inspection modal opened when tapping any badge in the Trophy Gallery.
 */
@Composable
fun BadgeDetailDialog(
    badge: AchievementBadge,
    onDismiss: () -> Unit
) {
    val maz = LocalMazColors.current
    val palette = tierPalette(badge.tier, badge.isUnlocked)
    val dateLabel = remember(badge.unlockedAt) {
        badge.unlockedAt?.let {
            SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault()).format(Date(it))
        }
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(26.dp),
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                    Surface(shape = CircleShape, color = palette.pillBg) {
                        Text(
                            text = badge.tier.labelBm.uppercase(),
                            style = MaterialTheme.typography.labelSmall,
                            color = palette.pillText,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                    Surface(shape = CircleShape, color = maz.indigoSoft) {
                        Text(
                            text = "${badge.category.iconEmoji} ${badge.category.labelBm}",
                            style = MaterialTheme.typography.labelSmall,
                            color = maz.onIndigoSoft,
                            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
                        )
                    }
                }

                Spacer(Modifier.height(2.dp))
                TierSealMedal(badge = badge, size = 94.dp)

                Text(
                    text = badge.title,
                    style = MaterialTheme.typography.headlineMedium,
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = badge.milestoneTip,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    color = if (badge.isUnlocked) maz.jadeSoft else maz.subtleSurface,
                    border = BorderStroke(1.2.dp, if (badge.isUnlocked) maz.jade else maz.cardBorder)
                ) {
                    Column(
                        modifier = Modifier.padding(14.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = if (badge.isUnlocked) "Status Pencapaian" else "Kemajuan Semasa",
                                style = MaterialTheme.typography.labelSmall,
                                color = if (badge.isUnlocked) maz.onJadeSoft else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = badge.progressText,
                                style = MaterialTheme.typography.labelMedium,
                                color = if (badge.isUnlocked) maz.jade else MaterialTheme.colorScheme.primary
                            )
                        }
                        LinearProgressIndicator(
                            progress = { badge.progressFraction },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(8.dp)
                                .clip(CircleShape),
                            color = if (badge.isUnlocked) maz.jade else IndigoMid,
                            trackColor = maz.cardBorder
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = if (badge.isUnlocked && dateLabel != null) "Dibuka: $dateLabel" else "Ganjaran apabila dibuka",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (badge.isUnlocked) maz.onJadeSoft else MaterialTheme.colorScheme.onSurfaceVariant
                            )
                            Text(
                                text = "+${badge.xpReward} XP",
                                style = MaterialTheme.typography.labelMedium,
                                color = maz.amberDark,
                                fontWeight = FontWeight.Black
                            )
                        }
                    }
                }
            }
        },
        confirmButton = {
            TactilePrimaryButton(
                text = "Tutup",
                onClick = onDismiss,
                modifier = Modifier.testTag("badge_detail_close_btn")
            )
        }
    )
}

/**
 * Compact next-milestone motivational nudge displayed in the Academy dashboard.
 */
@Composable
fun NextMilestoneNudgeCard(
    nextBadge: AchievementBadge,
    unlockedCount: Int,
    totalCount: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val maz = LocalMazColors.current
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .testTag("next_milestone_nudge")
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(16.dp),
        color = maz.amberSoft.copy(alpha = if (maz.isDark) 0.55f else 0.85f),
        border = BorderStroke(1.2.dp, maz.amber.copy(alpha = 0.55f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(38.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.surface),
                contentAlignment = Alignment.Center
            ) {
                Text(text = nextBadge.emoji, fontSize = 19.sp)
            }
            Column(modifier = Modifier.weight(1f)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "LENCANA SETERUSNYA · $unlockedCount/$totalCount",
                        style = MaterialTheme.typography.labelSmall,
                        fontSize = 9.5.sp,
                        color = maz.onAmberSoft
                    )
                    Text(
                        text = nextBadge.progressText,
                        style = MaterialTheme.typography.labelSmall,
                        color = maz.onAmberSoft
                    )
                }
                Text(
                    text = "${nextBadge.title} (+${nextBadge.xpReward} XP)",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurface,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(Modifier.height(4.dp))
                LinearProgressIndicator(
                    progress = { nextBadge.progressFraction },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp)
                        .clip(CircleShape),
                    color = AmberGold,
                    trackColor = MaterialTheme.colorScheme.surface.copy(alpha = 0.7f)
                )
            }
            Icon(
                imageVector = Icons.Rounded.ChevronRight,
                contentDescription = "Lihat Dewan Lencana",
                tint = maz.onAmberSoft
            )
        }
    }
}

/**
 * Live floating celebration banner that drops in whenever a student hits a milestone
 * and unlocks a brand-new achievement badge.
 */
@Composable
fun BadgeUnlockCelebrationOverlay(
    badge: AchievementBadge?,
    remainingCount: Int,
    onViewTrophies: () -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    var popped by remember(badge?.id) { mutableStateOf(false) }
    LaunchedEffect(badge?.id) {
        if (badge != null) popped = true
    }
    val medalScale by animateFloatAsState(
        targetValue = if (popped) 1f else 0.55f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessMedium
        ),
        label = "badgeUnlockScale"
    )

    AnimatedVisibility(
        visible = badge != null,
        enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
        exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut(),
        modifier = modifier
    ) {
        if (badge != null) {
            val palette = tierPalette(badge.tier, isUnlocked = true)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 14.dp, vertical = 10.dp)
                    .testTag("badge_unlock_celebration_banner"),
                shape = RoundedCornerShape(22.dp),
                color = NavyDeep,
                border = BorderStroke(2.dp, palette.ringColor),
                shadowElevation = 12.dp
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            Brush.horizontalGradient(
                                listOf(NavyDeep, NavySurface, palette.ringColor.copy(alpha = 0.3f))
                            )
                        )
                        .padding(14.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        TierSealMedal(
                            badge = badge,
                            size = 64.dp,
                            modifier = Modifier.scale(medalScale)
                        )

                        Column(modifier = Modifier.weight(1f)) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Rounded.AutoAwesome,
                                    contentDescription = null,
                                    tint = AmberBright,
                                    modifier = Modifier.size(14.dp)
                                )
                                Text(
                                    text = "LENCANA BAHARU DIBUKA!",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = AmberBright
                                )
                            }
                            Spacer(Modifier.height(2.dp))
                            Text(
                                text = badge.title,
                                style = MaterialTheme.typography.titleMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Black
                            )
                            Text(
                                text = "${badge.desc} · +${badge.xpReward} XP",
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(0xFFCBD5E1),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )
                            Spacer(Modifier.height(6.dp))
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Surface(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .clickable(onClick = onViewTrophies),
                                    shape = CircleShape,
                                    color = AmberGold
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        Icon(
                                            Icons.Rounded.EmojiEvents,
                                            contentDescription = null,
                                            tint = NavyDeep,
                                            modifier = Modifier.size(13.dp)
                                        )
                                        Text(
                                            text = "Lihat Lencana",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = NavyDeep,
                                            fontWeight = FontWeight.Black
                                        )
                                    }
                                }
                                if (remainingCount > 0) {
                                    Text(
                                        text = "+$remainingCount lagi",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = AmberBright
                                    )
                                }
                            }
                        }

                        IconButton(
                            onClick = onDismiss,
                            modifier = Modifier
                                .size(36.dp)
                                .align(Alignment.Top)
                                .testTag("dismiss_badge_unlock_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Close,
                                contentDescription = "Tutup notifikasi lencana",
                                tint = Color.White.copy(alpha = 0.8f)
                            )
                        }
                    }
                }
            }
        }
    }
}
