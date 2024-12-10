package com.mohaberabi.tatbeeq.platform_module

import UrlLauncher
import androidx.room.Room
import androidx.room.RoomDatabase
import coil3.PlatformContext
import com.mohaberabi.tatbeeq.core.data.database.AppDatabase
import com.mohaberabi.tatbeeq.core.util.PrefsDataStore

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi
import org.koin.dsl.module
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSHomeDirectory
import platform.Foundation.NSUserDomainMask


@OptIn(ExperimentalForeignApi::class)
actual val platformModule = module {
    single<PrefsDataStore> {
        createDataStore()
    }
    single {
        UrlLauncher()
    }
    single {
        ShareManager(
            dispatchers = get()
        )
    }
    single<RoomDatabase.Builder<AppDatabase>> {
        val documentsDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = true,
            error = null,
        )?.path ?: NSHomeDirectory()
        val dbFilePath = "$documentsDirectory/tatbeeq_database.db"
        Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
        )
    }
}