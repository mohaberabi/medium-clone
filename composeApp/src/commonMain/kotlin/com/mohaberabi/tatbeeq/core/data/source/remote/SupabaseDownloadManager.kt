package com.mohaberabi.tatbeeq.core.data.source.remote

import com.mohaberabi.tatbeeq.core.data.network.tryNetworkRequest
import com.mohaberabi.tatbeeq.core.domain.source.remote.DownloadManager
import com.mohaberabi.tatbeeq.platform_module.DispatchersProvider
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.storage.storage
import kotlinx.coroutines.withContext

class SupabaseDownloadManager(
    private val dispatchers: DispatchersProvider,
    private val client: SupabaseClient,
) : DownloadManager {
    override suspend fun downloadFile(
        path: String,
        fileName: String
    ): ByteArray {
        return withContext(dispatchers.io) {
            tryNetworkRequest {
                val bucket = client.storage.from(path)
                bucket.downloadPublic(fileName)
            }
        }
    }
}