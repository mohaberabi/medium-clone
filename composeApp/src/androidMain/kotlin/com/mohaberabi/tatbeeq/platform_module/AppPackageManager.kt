package com.mohaberabi.tatbeeq.platform_module

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

actual class AppPackageManager(
    private val context: Context,
) {

    actual fun getAppVersion(): String {
        val packageManager = context.packageManager.getPackageInfo(context.packageName, 0)
        return packageManager.versionName
    }

}


@Composable
actual fun rememberPackageManager(): AppPackageManager {
    val context = LocalContext.current
    return remember {
        AppPackageManager(context)
    }
}