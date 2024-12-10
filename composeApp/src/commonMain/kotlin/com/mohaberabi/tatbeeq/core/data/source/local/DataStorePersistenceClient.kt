package com.mohaberabi.tatbeeq.core.data.source.local

import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.mohaberabi.tatbeeq.core.domain.source.local.PersistenceClient
import com.mohaberabi.tatbeeq.core.util.PrefsDataStore
import com.mohaberabi.tatbeeq.core.util.error.AppException
import com.mohaberabi.tatbeeq.core.util.error.ErrorModel
import com.mohaberabi.tatbeeq.core.util.error.RemoteError
import com.mohaberabi.tatbeeq.platform_module.DispatchersProvider

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException

class DataStorePersistenceClient(
    private val dispatchers: DispatchersProvider,
    private val datastore: PrefsDataStore,
) : PersistenceClient {
    override suspend fun set(
        key: String,
        value: String,
    ) {
        withContext(dispatchers.io) {
            datastore.edit { it[stringPreferencesKey(key)] = value }
        }

    }

    override fun read(
        key: String,
    ): Flow<String?> {
        return datastore.data.map {
            it[stringPreferencesKey(key)]
        }.flowOn(dispatchers.io)
    }


    override suspend fun clear() {
        withContext(dispatchers.io) {
            datastore.edit { it.clear() }
        }
    }


    override suspend fun delete(key: String) {
        withContext(dispatchers.io) {
            datastore.edit { it.remove(stringPreferencesKey(key)) }
        }
    }


}

suspend inline fun <reified T> tryLocalOperation(
    execute: () -> T
): T {
    return try {
        val res = execute()
        res
    } catch (e: Exception) {
        if (e is kotlinx.coroutines.CancellationException) throw e
        e.printStackTrace()
        val appError = when (e) {
            is IOException -> RemoteError.NO_NETWORK
            is SerializationException -> RemoteError.SERIALIZATION_ERROR
            else -> RemoteError.UNKNOWN_ERROR
        }
        val error = ErrorModel(appError)
        throw AppException(error)
    }
}

