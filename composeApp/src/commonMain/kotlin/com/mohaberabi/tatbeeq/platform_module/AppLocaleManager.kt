package com.mohaberabi.tatbeeq.platform_module

import androidx.compose.runtime.Composable
import com.mohaberabi.tatbeeq.core.presentation.design.AppLang


interface AppLocaleManager {
    fun getLocale(): String
}

@Composable
expect fun rememberAppLocale(): AppLang