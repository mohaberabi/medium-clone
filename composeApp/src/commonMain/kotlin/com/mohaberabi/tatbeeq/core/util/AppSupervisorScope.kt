package com.mohaberabi.tatbeeq.core.util

import com.mohaberabi.tatbeeq.platform_module.DispatchersProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob


interface AppSupervisorScope {
    val superVisor: CoroutineScope
}


class DefaultSuperVisorScope(
    private val dispatchers: DispatchersProvider
) : AppSupervisorScope {
    override val superVisor: CoroutineScope
        get() = CoroutineScope(
            dispatchers.io + SupervisorJob(),
        )
}