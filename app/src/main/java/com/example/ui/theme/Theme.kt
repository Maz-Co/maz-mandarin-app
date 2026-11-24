package com.example.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Immutable
data class MazExtendedColors(
    val jade: Color,
    val jadeSoft: Color,
    val onJadeSoft: Color,
    val coral: Color,
    val coralSoft: Color,
    val onCoralSoft: Color,
    val amber: Color,
    val amberDark: Color,
    val amberSoft: Color,
    val onAmberSoft: Color,
    val indigoSoft: Color,
    val onIndigoSoft: Color,
    val cardBorder: Color,
    val cardShadow3d: Color,
    val subtleSurface: Color,
    val isDark: Boolean
)

val LocalMazColors = staticCompositionLocalOf {
    MazExtendedColors(
        jade = JadeImperial,
        jadeSoft = JadeSoft,
        onJadeSoft = JadeDark,
        coral = CoralCrimson,
        coralSoft = CoralSoft,
        onCoralSoft = CoralDark,
        amber = AmberGold,
        amberDark = AmberDark,
        amberSoft = AmberSoft,
        onAmberSoft = AmberDark,
        indigoSoft = IndigoSoft,
        onIndigoSoft = IndigoRoyal,
        cardBorder = BorderLight,
        cardShadow3d = Shadow3DLight,
        subtleSurface = SurfaceSubtleLight,
        isDark = false
    )
}

private val MazLightColorScheme = lightColorScheme(
    primary = IndigoMid,
    onPrimary = Color.White,
    primaryContainer = IndigoSoft,
    onPrimaryContainer = IndigoRoyal,
    secondary = CoralCrimson,
    onSecondary = Color.White,
    secondaryContainer = CoralSoft,
    onSecondaryContainer = CoralDark,
    tertiary = JadeImperial,
    onTertiary = Color.White,
    tertiaryContainer = JadeSoft,
    onTertiaryContainer = JadeDark,
    background = CanvasLight,
    onBackground = NavyDeep,
    surface = SurfaceWhite,
    onSurface = NavyDeep,
    surfaceVariant = SurfaceSubtleLight,
    onSurfaceVariant = MutedSlateLight,
    outline = BorderLight,
    error = CoralCrimson,
    onError = Color.White,
    errorContainer = CoralSoft,
    onErrorContainer = CoralDark
)

private val MazDarkColorScheme = darkColorScheme(
    primary = IndigoBright,
    onPrimary = NavyDarkBg,
    primaryContainer = IndigoSoftDark,
    onPrimaryContainer = IndigoSoft,
    secondary = Color(0xFFFB7185),
    onSecondary = NavyDarkBg,
    secondaryContainer = CoralSoftDark,
    onSecondaryContainer = CoralSoft,
    tertiary = JadeBright,
    onTertiary = NavyDarkBg,
    tertiaryContainer = JadeSoftDark,
    onTertiaryContainer = JadeSoft,
    background = NavyDarkBg,
    onBackground = TextLight,
    surface = NavyCardDark,
    onSurface = TextLight,
    surfaceVariant = NavySurface,
    onSurfaceVariant = MutedSlateDark,
    outline = BorderDark,
    error = Color(0xFFFB7185),
    onError = NavyDarkBg,
    errorContainer = CoralSoftDark,
    onErrorContainer = CoralSoft
)

private val MazShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(22.dp),
    extraLarge = RoundedCornerShape(28.dp)
)

@Composable
fun MazMandarinTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) MazDarkColorScheme else MazLightColorScheme
    val extendedColors = if (darkTheme) {
        MazExtendedColors(
            jade = JadeBright,
            jadeSoft = JadeSoftDark,
            onJadeSoft = JadeBright,
            coral = Color(0xFFFB7185),
            coralSoft = CoralSoftDark,
            onCoralSoft = Color(0xFFFDA4AF),
            amber = AmberBright,
            amberDark = AmberGold,
            amberSoft = AmberSoftDark,
            onAmberSoft = AmberBright,
            indigoSoft = IndigoSoftDark,
            onIndigoSoft = Color(0xFFC7D2FE),
            cardBorder = BorderDark,
            cardShadow3d = Shadow3DDark,
            subtleSurface = NavySurface,
            isDark = true
        )
    } else {
        MazExtendedColors(
            jade = JadeImperial,
            jadeSoft = JadeSoft,
            onJadeSoft = JadeDark,
            coral = CoralCrimson,
            coralSoft = CoralSoft,
            onCoralSoft = CoralDark,
            amber = AmberGold,
            amberDark = AmberDark,
            amberSoft = AmberSoft,
            onAmberSoft = Color(0xFF92400E),
            indigoSoft = IndigoSoft,
            onIndigoSoft = IndigoRoyal,
            cardBorder = BorderLight,
            cardShadow3d = Shadow3DLight,
            subtleSurface = SurfaceSubtleLight,
            isDark = false
        )
    }

    CompositionLocalProvider(LocalMazColors provides extendedColors) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = MazTypography,
            shapes = MazShapes,
            content = content
        )
    }
}
