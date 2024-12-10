package com.mohaberabi.tatbeeq.core.domain.source.local

import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import kotlinx.coroutines.flow.Flow

interface ThemeModeLocalDataSource {

    fun getThemeMode(): Flow<AppThemeMode>


    suspend fun changeThemeMode(
        mode: AppThemeMode,
    )
}