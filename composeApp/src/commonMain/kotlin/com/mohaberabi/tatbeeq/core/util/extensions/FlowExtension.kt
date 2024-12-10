package com.mohaberabi.tatbeeq.core.util.extensions

import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import com.mohaberabi.tatbeeq.core.util.error.CommonError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

typealias FlowResult<T> = Flow<AppResult<T, AppError>>

fun <T> Flow<T>.asResult(): FlowResult<T> {
    val res = map<T, AppResult<T, AppError>> { AppResult.Done(it) }
        .catch {
            it.printStackTrace()
            emit(AppResult.Error(CommonError.UNKNOWN))
        }
    return res
}
