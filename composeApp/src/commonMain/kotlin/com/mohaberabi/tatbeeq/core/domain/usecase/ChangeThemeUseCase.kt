package com.mohaberabi.tatbeeq.core.domain.usecase

import com.mohaberabi.tatbeeq.core.domain.repository.AppPrefsRepository
import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode

class ChangeThemeUseCase(
    private val appPrefsRepository: AppPrefsRepository,
) {

    suspend operator fun invoke(
        mode: AppThemeMode,
    ) = appPrefsRepository.changeThemeMode(mode)
}