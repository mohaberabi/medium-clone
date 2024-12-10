package com.mohaberabi.tatbeeq.platform_module

import platform.Foundation.NSBundle
import platform.Foundation.NSDictionary
import platform.Foundation.NSProcessInfo
import platform.Foundation.dictionaryWithContentsOfFile
import platform.Foundation.dictionaryWithContentsOfURL
import platform.Foundation.dictionaryWithDictionary

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual object AppSecrets {

    actual val supabaseSecret: String
        get() {
            val key = NSBundle.mainBundle.pathForResource("Secrets", "plist")?.let {
                val map = NSDictionary.dictionaryWithContentsOfFile(it)
                map?.get("supabaseKey") as? String
            }
            return key ?: ""
        }
}


