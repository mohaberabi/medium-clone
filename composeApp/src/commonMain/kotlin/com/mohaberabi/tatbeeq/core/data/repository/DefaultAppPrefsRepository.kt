package com.mohaberabi.tatbeeq.core.data.repository

import com.mohaberabi.tatbeeq.core.domain.repository.AppPrefsRepository
import com.mohaberabi.tatbeeq.core.domain.source.local.ThemeModeLocalDataSource
import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode
import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import kotlinx.coroutines.flow.Flow

class DefaultAppPrefsRepository(
    private val themeModeLocalDataSource: ThemeModeLocalDataSource
) : AppPrefsRepository {
    override fun getThemeMode(): Flow<AppThemeMode> = themeModeLocalDataSource.getThemeMode()
    override suspend fun changeThemeMode(
        mode: AppThemeMode,
    ): EmptyDataResult<AppError> {
        themeModeLocalDataSource.changeThemeMode(mode)
        return AppResult.Done(Unit)
    }
}