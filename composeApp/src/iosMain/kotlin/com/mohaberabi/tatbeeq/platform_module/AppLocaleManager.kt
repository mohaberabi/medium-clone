package com.mohaberabi.tatbeeq.platform_module

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import com.mohaberabi.tatbeeq.core.presentation.design.AppLang
import platform.Foundation.NSLocale
import platform.Foundation.currentLocale
import platform.Foundation.languageCode


class IosAppLocaleManager : AppLocaleManager {
    override fun getLocale(): String {
        val nsLocale =
            NSLocale.currentLocale.languageCode
        return nsLocale
    }
}


@Composable
actual fun rememberAppLocale(): AppLang {
    val nsLocale = IosAppLocaleManager().getLocale()
    return remember(nsLocale) {
        when (nsLocale) {
            "ar" -> AppLang.Arabic
            else -> AppLang.English
        }
    }
}