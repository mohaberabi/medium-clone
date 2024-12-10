package com.mohaberabi.tatbeeq.core.presentation.design

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

import org.jetbrains.compose.resources.Font
import tatbeeq.composeapp.generated.resources.Res
import tatbeeq.composeapp.generated.resources.*

val lotaFontFamily
    @Composable
    get() = FontFamily(
        Font(Res.font.lota_bold, FontWeight.Bold),
        Font(Res.font.lota_light, FontWeight.Light),
        Font(Res.font.lota_regular, FontWeight.Normal),
        Font(Res.font.lota_semibold, FontWeight.SemiBold),
        Font(Res.font.lota_black, FontWeight.Black),
    )
val maraiFontFamily
    @Composable
    get() =
        FontFamily(
            Font(Res.font.marai_bold, FontWeight.Bold),
            Font(Res.font.marai_light, FontWeight.Light),
            Font(Res.font.marai_regular, FontWeight.Normal),
        )

@Composable
fun appTypography(
    lang: AppLang = AppLang.English,
    isDark: Boolean = false,
): Typography {
    val family = when (lang) {
        AppLang.Arabic -> maraiFontFamily
        else -> lotaFontFamily
    }
    val baseStyle = TextStyle(
        color = if (isDark) Color.White else Color.Black,
        fontFamily = family,
    )
    return Typography(
        displayLarge = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 57.sp,
            lineHeight = 64.sp,
            letterSpacing = -0.25.sp
        ),
        displayMedium = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 45.sp,
            lineHeight = 52.sp,
            letterSpacing = 0.sp
        ),
        displaySmall = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 36.sp,
            lineHeight = 44.sp,
            letterSpacing = 0.sp
        ),
        headlineLarge = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 32.sp,
            lineHeight = 40.sp,
            letterSpacing = 0.sp
        ),
        headlineMedium = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 28.sp,
            lineHeight = 36.sp,
            letterSpacing = 0.sp
        ),
        headlineSmall = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            lineHeight = 32.sp,
            letterSpacing = 0.sp
        ),
        titleLarge = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 22.sp,
            lineHeight = 28.sp,
            letterSpacing = 0.sp
        ),
        titleMedium = baseStyle.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.15.sp
        ),
        titleSmall = baseStyle.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        bodyLarge = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 16.sp,
            lineHeight = 24.sp,
            letterSpacing = 0.5.sp
        ),
        bodyMedium = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.25.sp
        ),
        bodySmall = baseStyle.copy(
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.4.sp
        ),
        labelLarge = baseStyle.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 14.sp,
            lineHeight = 20.sp,
            letterSpacing = 0.1.sp
        ),
        labelMedium = baseStyle.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        ),
        labelSmall = baseStyle.copy(
            fontWeight = FontWeight.Medium,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            letterSpacing = 0.5.sp
        )
    )

}