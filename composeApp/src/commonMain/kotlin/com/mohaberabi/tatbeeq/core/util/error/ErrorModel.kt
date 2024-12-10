package com.mohaberabi.tatbeeq.core.util.error


data class ErrorModel(
    val appError: AppError,
    val status: Int? = null,
    val cause: Throwable? = null,
) : AppError


data class AppException(val error: ErrorModel) : Throwable()