package com.mohaberabi.tatbeeq.core.util

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import com.mohaberabi.tatbeeq.core.util.error.AppError
import com.mohaberabi.tatbeeq.core.util.error.CommonError
import com.mohaberabi.tatbeeq.core.util.error.LocalError
import com.mohaberabi.tatbeeq.core.util.error.RemoteError

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString
import org.jetbrains.compose.resources.stringResource
import tatbeeq.composeapp.generated.resources.Res
import tatbeeq.composeapp.generated.resources.*


sealed class UiText {

    data object Empty : UiText()

    data class Dynamic(val value: String) : UiText()

    data class StringRes(val id: StringResource, val formatArgs: List<Any> = listOf()) : UiText()


    @Composable
    fun asString(): String {
        return when (this) {
            is Dynamic -> this.value
            Empty -> ""
            is StringRes -> stringResource(this.id)
        }
    }

    suspend fun getAsString(
    ): String {
        return when (this) {
            is Dynamic -> this.value
            Empty -> ""
            is StringRes -> getString(this.id, this.formatArgs.toTypedArray())
        }
    }

    @Composable
    fun AsText(
        modifier: Modifier = Modifier,
        style: TextStyle = TextStyle(),
    ) {
        val value = asString()
        Text(value, style = style, modifier = modifier)
    }


    val isEmpty: Boolean
        get() = this == Empty
}


fun AppError.asUiText(): UiText {
    val id = when (this) {
        is CommonError -> {
            when (this) {
                CommonError.IO_ERROR -> Res.string.error_io_common
                CommonError.UNKNOWN -> Res.string.error_unknown_common
                CommonError.SERIALIZATION_ERROR -> Res.string.error_unknown_common
            }
        }

        is RemoteError -> {
            when (this) {
                RemoteError.REQUEST_TIMEOUT -> Res.string.error_request_timeout
                RemoteError.UNAUTHORIZED -> Res.string.error_unauthorized
                RemoteError.CLIENT_ERROR -> Res.string.error_client_error
                RemoteError.CONFLICT -> Res.string.error_conflict
                RemoteError.TOO_MANY_REQUEST -> Res.string.error_too_many_requests
                RemoteError.NO_NETWORK -> Res.string.error_no_network
                RemoteError.PAYLOAD_TOO_LARGE -> Res.string.error_payload_too_large
                RemoteError.SERVER_ERROR -> Res.string.error_server_error
                RemoteError.SERIALIZATION_ERROR -> Res.string.error_serialization_error
                RemoteError.UNKNOWN_ERROR -> Res.string.error_unknown_remote
            }
        }

        is LocalError -> {
            when (this) {
                LocalError.DISK_FULL -> Res.string.error_disk_full
                LocalError.UNKNOWN -> Res.string.error_unknown
                LocalError.IO -> Res.string.error_io
                LocalError.Corrupted -> Res.string.error_corrupted
                LocalError.FILE_SAVE_ERROR -> Res.string.error_corrupted
            }
        }

        else -> Res.string.error_unknown
    }
    return UiText.StringRes(id)
}

