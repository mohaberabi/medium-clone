package com.mohaberabi.tatbeeq.platform_module

import androidx.compose.runtime.Composable

expect class AppPackageManager {
    fun getAppVersion(): String
}


@Composable
expect fun rememberPackageManager(): AppPackageManager