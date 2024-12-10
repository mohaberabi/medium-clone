package com.mohaberabi.tatbeeq.platform_module

import android.os.Build
import com.mohaberabi.tatbeeq.BuildConfig


actual object AppSecrets {

    actual val supabaseSecret = BuildConfig.supabaseSecret
}