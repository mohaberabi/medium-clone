package com.mohaberabi.tatbeeq.core.domain.repository

import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import kotlinx.coroutines.flow.Flow


interface AppPrefsRepository {
    fun getThemeMode(): Flow<AppThemeMode>


    suspend fun changeThemeMode(
        mode: AppThemeMode,
    ): EmptyDataResult<AppError>
}