package com.mohaberabi.tatbeeq.core.presentation.design

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import org.jetbrains.compose.resources.StringResource
import tatbeeq.composeapp.generated.resources.Res
import tatbeeq.composeapp.generated.resources.ar
import tatbeeq.composeapp.generated.resources.dark
import tatbeeq.composeapp.generated.resources.en
import tatbeeq.composeapp.generated.resources.light
import tatbeeq.composeapp.generated.resources.system


enum class AppLang(
    val code: String,
    val stringRes: StringResource
) {
    English("en", Res.string.en),
    Arabic("ar", Res.string.ar)
}

enum class AppThemeMode(
    val stringRes: StringResource
) {
    Light(Res.string.light),
    Dark(Res.string.dark),
    System(Res.string.system),

}

@Composable
fun AppTheme(
    themeMode: AppThemeMode = AppThemeMode.System,
    lang: AppLang = AppLang.English,
    content: @Composable () -> Unit,
) {
    val isDark = rememberThemeMode(
        themeMode = themeMode
    )
    val typography = appTypography(
        lang = lang,
        isDark = isDark
    )
    val colorScheme = if (isDark) {
        darkScheme
    } else {
        lightScheme
    }
    MaterialTheme(
        typography = typography,
        content = content,
        colorScheme = colorScheme
    )
}

@Composable
fun rememberThemeMode(
    themeMode: AppThemeMode = AppThemeMode.System,
): Boolean {
    val isDark = when (themeMode) {
        AppThemeMode.System -> isSystemInDarkTheme()
        AppThemeMode.Light -> false
        else -> true
    }
    return remember(isDark) {
        isDark
    }
}