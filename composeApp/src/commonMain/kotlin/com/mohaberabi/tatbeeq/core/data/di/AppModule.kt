package com.mohaberabi.tatbeeq.core.data.di


import androidx.lifecycle.SavedStateHandle
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

import com.mohaberabi.tatbeeq.app.navigation.toplevel.TopLevelRouteViewModel
import com.mohaberabi.tatbeeq.app.viewmodel.MainViewModel
import com.mohaberabi.tatbeeq.core.data.database.AppDatabase
import com.mohaberabi.tatbeeq.core.data.network.SupaBaseFactory
import com.mohaberabi.tatbeeq.core.data.repository.DefaultAppPrefsRepository
import com.mohaberabi.tatbeeq.core.data.source.local.DataStorePersistenceClient
import com.mohaberabi.tatbeeq.core.data.source.local.ThemeModeDataStore
import com.mohaberabi.tatbeeq.core.data.source.remote.SupabaseDownloadManager
import com.mohaberabi.tatbeeq.core.domain.repository.AppPrefsRepository
import com.mohaberabi.tatbeeq.core.domain.source.local.PersistenceClient
import com.mohaberabi.tatbeeq.core.domain.source.local.ThemeModeLocalDataSource
import com.mohaberabi.tatbeeq.core.domain.source.remote.DownloadManager
import com.mohaberabi.tatbeeq.core.domain.usecase.ChangeThemeUseCase
import com.mohaberabi.tatbeeq.core.domain.usecase.GetThemeModeUseCase
import com.mohaberabi.tatbeeq.platform_module.DispatchersProvider
import io.github.jan.supabase.SupabaseClient

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val appModule = module {
    single<AppDatabase> {
        val builder: RoomDatabase.Builder<AppDatabase> = get()
        builder
            .fallbackToDestructiveMigrationOnDowngrade(dropAllTables = true)
            .fallbackToDestructiveMigration(dropAllTables = true)
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    single<PersistenceClient> {

        DataStorePersistenceClient(
            get(),
            get()
        )
    }

    single<ThemeModeLocalDataSource> {
        ThemeModeDataStore(get())
    }
    single<AppPrefsRepository> {
        DefaultAppPrefsRepository(get())
    }
    single {
        GetThemeModeUseCase(get())
    }
    single {
        ChangeThemeUseCase(get())
    }
    single<DispatchersProvider> {
        DispatchersProvider()
    }

    factory {
        SavedStateHandle()
    }
    viewModelOf(::TopLevelRouteViewModel)


    single<SupabaseClient> {
        SupaBaseFactory.create()
    }

    single<DownloadManager> {
        SupabaseDownloadManager(
            dispatchers = get(),
            client = get()
        )
    }
    viewModelOf(::MainViewModel)
}


