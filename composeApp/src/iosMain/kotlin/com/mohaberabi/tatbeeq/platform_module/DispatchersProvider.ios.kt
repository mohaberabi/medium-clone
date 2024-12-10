package com.mohaberabi.tatbeeq.platform_module

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
actual class DispatchersProvider {
    actual val io: CoroutineDispatcher
        get() = Dispatchers.IO
    actual val main: CoroutineDispatcher
        get() = Dispatchers.Main
    actual val default: CoroutineDispatcher
        get() = Dispatchers.Default
    actual val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
    
}