package com.mohaberabi.tatbeeq.features.settings.viewmodel

import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode

sealed interface SettingsActions {


    data class ChangeTheme(val mode: AppThemeMode) : SettingsActions

}