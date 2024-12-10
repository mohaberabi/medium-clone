package com.mohaberabi.tatbeeq.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.Modifier
import com.mohaberabi.tatbeeq.core.presentation.design.AppLang
import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode

val LocalAppLanguage = compositionLocalOf<AppLang> { AppLang.English }

val LocalThemeMode = compositionLocalOf<AppThemeMode> { AppThemeMode.System }
val LocalSnackBarController = compositionLocalOf<SnackBarController> { SnackBarController }


@Composable
fun AppCompositionLocals(
    currentLang: AppLang,
    themeMode: AppThemeMode,
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalSnackBarController provides SnackBarController,
        LocalAppLanguage provides currentLang,
        LocalThemeMode provides themeMode,
        content = content
    )
}