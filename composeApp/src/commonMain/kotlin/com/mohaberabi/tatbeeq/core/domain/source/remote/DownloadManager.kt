package com.mohaberabi.tatbeeq.core.domain.source.remote

import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.error.RemoteError

interface DownloadManager {


    suspend fun downloadFile(
        path: String,
        fileName: String
    ): ByteArray
}