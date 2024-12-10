package com.mohaberabi.tatbeeq.core.data.source.local

import com.mohaberabi.tatbeeq.core.domain.source.local.PersistenceClient
import com.mohaberabi.tatbeeq.core.domain.source.local.ThemeModeLocalDataSource
import com.mohaberabi.tatbeeq.core.presentation.design.AppTheme
import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ThemeModeDataStore(
    private val persistenceClient: PersistenceClient
) : ThemeModeLocalDataSource {
    companion object {
        private const val THEME_KEY = "themeKey"
    }

    override fun getThemeMode(): Flow<AppThemeMode> =
        persistenceClient.read(
            THEME_KEY,
        ).map {
            it?.let {
                AppThemeMode.valueOf(it)
            } ?: AppThemeMode.System
        }

    override suspend fun changeThemeMode(
        mode: AppThemeMode,
    ) {
        return tryLocalOperation {
            persistenceClient.set(THEME_KEY, mode.name)
        }
    }
}