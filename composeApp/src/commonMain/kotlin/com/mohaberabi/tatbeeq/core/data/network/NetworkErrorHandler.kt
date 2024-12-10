package com.mohaberabi.tatbeeq.core.data.network

import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.error.AppException
import com.mohaberabi.tatbeeq.core.util.error.CommonError
import com.mohaberabi.tatbeeq.core.util.error.ErrorModel
import com.mohaberabi.tatbeeq.core.util.error.RemoteError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.serialization.SerializationException

suspend inline fun <reified T> tryNetworkRequest(
    execute: () -> T
): T {
    return try {
        val res = execute()
        res
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        val appError = when (e) {
            is UnresolvedAddressException -> RemoteError.NO_NETWORK
            is SerializationException -> RemoteError.SERIALIZATION_ERROR
            is ClientRequestException -> RemoteError.CLIENT_ERROR
            is ServerResponseException -> RemoteError.SERVER_ERROR
            is TimeoutCancellationException -> RemoteError.REQUEST_TIMEOUT
            else -> RemoteError.UNKNOWN_ERROR
        }
        val error = ErrorModel(appError)
        throw AppException(error)
    }
}

