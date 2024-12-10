package com.mohaberabi.tatbeeq.app

import com.mohaberabi.tatbeeq.core.util.UiText
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow


sealed class SnackBarEvents {

    data class Message(
        val message: String,
    ) : SnackBarEvents()

    data class UiTextMessage(
        val message: UiText,
    ) : SnackBarEvents()

}


object SnackBarController {

    private val _events = Channel<SnackBarEvents>()
    val events = _events.receiveAsFlow()

    suspend fun sendEvent(event: SnackBarEvents) = _events.send(event)
}