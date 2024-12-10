package com.mohaberabi.tatbeeq.core.util.error

interface AppError


enum class LocalError : AppError {
    DISK_FULL,
    UNKNOWN,
    Corrupted,
    FILE_SAVE_ERROR,
    IO
}

enum class CommonError : AppError {
    IO_ERROR,
    UNKNOWN,
    SERIALIZATION_ERROR,
}


enum class RemoteError : AppError {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    CLIENT_ERROR,
    CONFLICT,
    TOO_MANY_REQUEST,
    NO_NETWORK,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION_ERROR,
    UNKNOWN_ERROR
}