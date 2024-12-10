package com.mohaberabi.tatbeeq.platform_module

import UrlLauncher
import android.content.Context
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import coil3.PlatformContext
import com.mohaberabi.tatbeeq.core.data.database.AppDatabase
import com.mohaberabi.tatbeeq.core.util.PrefsDataStore
import com.mohaberabi.tatbeeq.core.util.constants.dataStoreFileName
import okio.Path.Companion.toPath

import org.koin.dsl.module


actual val platformModule = module {
    single<AppDatabase> {

        createDatabase(get())
    }

    single<PrefsDataStore> {

        createDataStore(get())
    }
    single<PlatformContext> { get() }
    single {

        ShareManager(context = get(), dispatchers = get())
    }

    single {
        UrlLauncher(get())
    }
}

internal fun createDataStore(context: Context): PrefsDataStore {
    return PreferenceDataStoreFactory.createWithPath {
        context.filesDir.resolve(dataStoreFileName).absolutePath.toPath()
    }
}