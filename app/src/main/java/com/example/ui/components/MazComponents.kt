package com.example.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.VolumeUp
import androidx.compose.material.icons.rounded.LocalFireDepartment
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.IndigoMid
import com.example.ui.theme.IndigoRoyal
import com.example.ui.theme.LocalMazColors

/**
 * Tactile 3D-elevated Material 3 Card inspired by MAZ Mandarin's physical card-depth aesthetic.
 */
@Composable
fun TactileCard(
    modifier: Modifier = Modifier,
    containerColor: Color = MaterialTheme.colorScheme.surface,
    borderColor: Color = LocalMazColors.current.cardBorder,
    shadowColor: Color = LocalMazColors.current.cardShadow3d,
    cornerRadius: Dp = 20.dp,
    depth: Dp = 4.dp,
    onClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val animatedOffset by animateDpAsState(
        targetValue = if (onClick != null && isPressed) depth else 0.dp,
        animationSpec = spring(stiffness = 600f),
        label = "tactileCardOffset"
    )
    val shape = RoundedCornerShape(cornerRadius)

    Box(modifier = modifier.padding(bottom = depth)) {
        // Bottom 3D lip
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = depth)
                .clip(shape)
                .background(shadowColor)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .offset(y = animatedOffset)
                .clip(shape)
                .then(
                    if (onClick != null) {
                        Modifier.clickable(
                            interactionSource = interactionSource,
                            indication = androidx.compose.material3.ripple(),
                            onClick = onClick
                        )
                    } else Modifier
                ),
            shape = shape,
            color = containerColor,
            border = BorderStroke(1.5.dp, borderColor)
        ) {
            Column(content = content)
        }
    }
}

@Composable
fun TactilePrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null,
    gradientColors: List<Color> = listOf(IndigoMid, IndigoRoyal),
    shadowColor: Color = Color(0xFF1E1B4B)
) {
    val haptics = LocalHapticFeedback.current
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val depth = 4.dp
    val offset by animateDpAsState(
        targetValue = if (enabled && isPressed) depth else 0.dp,
        animationSpec = spring(stiffness = 650f),
        label = "btnOffset"
    )
    val shape = RoundedCornerShape(15.dp)

    Box(modifier = modifier.padding(bottom = depth)) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = depth)
                .clip(shape)
                .background(if (enabled) shadowColor else Color.Gray.copy(alpha = 0.35f))
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 50.dp)
                .offset(y = offset)
                .clip(shape)
                .background(
                    if (enabled) Brush.verticalGradient(gradientColors)
                    else Brush.verticalGradient(listOf(Color.Gray.copy(alpha = 0.4f), Color.Gray.copy(alpha = 0.5f)))
                )
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = androidx.compose.material3.ripple(color = Color.White),
                    onClick = {
                        haptics.performHapticFeedback(HapticFeedbackType.TextHandleMove)
                        onClick()
                    }
                )
                .padding(horizontal = 18.dp, vertical = 12.dp),
            contentAlignment = Alignment.Center
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(19.dp)
                    )
                    Spacer(Modifier.width(8.dp))
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }
}

@Composable
fun TactileSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leadingIcon: ImageVector? = null
) {
    val maz = LocalMazColors.current
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()
    val depth = 3.dp
    val offset by animateDpAsState(
        targetValue = if (enabled && isPressed) depth else 0.dp,
        animationSpec = spring(stiffness = 650f),
        label = "secBtnOffset"
    )
    val shape = RoundedCornerShape(14.dp)

    Box(modifier = modifier.padding(bottom = depth)) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(y = depth)
                .clip(shape)
                .background(maz.cardShadow3d)
        )
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 48.dp)
                .offset(y = offset)
                .clip(shape)
                .clickable(
                    enabled = enabled,
                    interactionSource = interactionSource,
                    indication = androidx.compose.material3.ripple(),
                    onClick = onClick
                ),
            shape = shape,
            color = MaterialTheme.colorScheme.surface,
            border = BorderStroke(1.5.dp, maz.cardBorder)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                if (leadingIcon != null) {
                    Icon(
                        imageVector = leadingIcon,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.onSurface.copy(alpha = if (enabled) 1f else 0.4f),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(Modifier.width(6.dp))
                }
                Text(
                    text = text,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = if (enabled) 1f else 0.4f)
                )
            }
        }
    }
}

@Composable
fun AudioChipButton(
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val maz = LocalMazColors.current
    Surface(
        modifier = modifier
            .heightIn(min = 48.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        shape = CircleShape,
        color = maz.jadeSoft,
        border = BorderStroke(1.5.dp, maz.jade.copy(alpha = 0.5f))
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Rounded.VolumeUp,
                contentDescription = label,
                tint = maz.onJadeSoft,
                modifier = Modifier.size(18.dp)
            )
            Spacer(Modifier.width(6.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.labelMedium,
                color = maz.onJadeSoft,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun StatPillBadge(
    icon: ImageVector,
    value: String,
    containerColor: Color,
    contentColor: Color,
    borderColor: Color,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        shape = CircleShape,
        color = containerColor,
        border = BorderStroke(1.2.dp, borderColor)
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = contentColor,
                modifier = Modifier.size(15.dp)
            )
            Text(
                text = value,
                style = MaterialTheme.typography.labelMedium,
                color = contentColor,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
fun StationCelebrationDialog(
    stationTitle: String,
    starsEarned: Int,
    xpEarned: Int,
    bestCombo: Int,
    hasNextStation: Boolean,
    onNextStation: () -> Unit,
    onBackToStations: () -> Unit
) {
    val maz = LocalMazColors.current
    AlertDialog(
        onDismissRequest = onBackToStations,
        containerColor = MaterialTheme.colorScheme.surface,
        shape = RoundedCornerShape(26.dp),
        title = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "🎉", fontSize = 44.sp)
                Spacer(Modifier.height(6.dp))
                Text(
                    text = "Stesen Selesai!",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    textAlign = TextAlign.Center
                )
                Text(
                    text = stationTitle,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center
                )
            }
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    repeat(3) { idx ->
                        val active = idx < starsEarned
                        Icon(
                            imageVector = Icons.Rounded.Star,
                            contentDescription = null,
                            tint = if (active) maz.amber else maz.cardBorder,
                            modifier = Modifier.size(38.dp)
                        )
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        color = maz.indigoSoft
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text("Ganjaran XP", style = MaterialTheme.typography.labelSmall, color = maz.onIndigoSoft)
                            Text("+$xpEarned XP", style = MaterialTheme.typography.titleLarge, color = maz.onIndigoSoft)
                        }
                    }
                    Surface(
                        modifier = Modifier.weight(1f),
                        shape = RoundedCornerShape(14.dp),
                        color = maz.coralSoft
                    ) {
                        Column(
                            modifier = Modifier.padding(12.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Rounded.LocalFireDepartment, contentDescription = null, tint = maz.coral, modifier = Modifier.size(14.dp))
                                Spacer(Modifier.width(2.dp))
                                Text("Kombo Terbaik", style = MaterialTheme.typography.labelSmall, color = maz.onCoralSoft)
                            }
                            Text("${bestCombo}x", style = MaterialTheme.typography.titleLarge, color = maz.onCoralSoft)
                        }
                    }
                }
            }
        },
        confirmButton = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (hasNextStation) {
                    TactilePrimaryButton(
                        text = "Teruskan Stesen Seterusnya →",
                        onClick = onNextStation
                    )
                }
                TactileSecondaryButton(
                    text = "Kembali ke 7 Stesen",
                    onClick = onBackToStations
                )
            }
        }
    )
}
