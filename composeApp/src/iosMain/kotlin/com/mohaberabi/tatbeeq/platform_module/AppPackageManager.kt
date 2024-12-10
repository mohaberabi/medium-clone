package com.mohaberabi.tatbeeq.platform_module

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import platform.Foundation.NSBundle

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class AppPackageManager() {

    companion object {
        private const val SHORT_VERSIONS = "CFBundleShortVersionString"
    }

    actual fun getAppVersion(): String {
        val mainBundle = NSBundle.mainBundle()
        val version = mainBundle
            .objectForInfoDictionaryKey(SHORT_VERSIONS) as? String
        return version ?: ""
    }
}


@Composable
actual fun rememberPackageManager(): AppPackageManager {
    return remember { AppPackageManager() }
}