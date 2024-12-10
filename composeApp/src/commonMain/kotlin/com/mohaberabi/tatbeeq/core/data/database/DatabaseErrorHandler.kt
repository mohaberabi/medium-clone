package com.mohaberabi.tatbeeq.core.data.database

import androidx.sqlite.SQLiteException
import com.mohaberabi.tatbeeq.core.util.error.AppException
import com.mohaberabi.tatbeeq.core.util.error.ErrorModel
import com.mohaberabi.tatbeeq.core.util.error.LocalError
import com.mohaberabi.tatbeeq.core.util.error.RemoteError

import kotlin.coroutines.cancellation.CancellationException


suspend inline fun <reified T> tryDataBaseOperation(
    execute: () -> T
): T {
    return try {
        val res = execute()
        res
    } catch (e: Exception) {
        if (e is CancellationException) throw e
        e.printStackTrace()
        val appError = when (e) {
            is SQLiteException -> LocalError.DISK_FULL
            else -> RemoteError.UNKNOWN_ERROR
        }
        val error = ErrorModel(appError)
        throw AppException(error)
    }
}

