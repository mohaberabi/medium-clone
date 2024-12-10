package com.mohaberabi.tatbeeq.core.domain.usecase

import com.mohaberabi.tatbeeq.core.domain.repository.AppPrefsRepository

class GetThemeModeUseCase(
    private val appPrefsRepository: AppPrefsRepository
) {


    operator fun invoke() = appPrefsRepository.getThemeMode()
}