package com.mohaberabi.tatbeeq.features.settings.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.tatbeeq.core.domain.usecase.ChangeThemeUseCase
import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode
import com.mohaberabi.tatbeeq.core.util.asUiText
import com.mohaberabi.tatbeeq.core.util.onFailure
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val changeThemeUseCase: ChangeThemeUseCase
) : ViewModel() {


    private val _events = Channel<SettingsEvents>()
    val events = _events.receiveAsFlow()


    fun onAction(action: SettingsActions) {
        when (action) {
            is SettingsActions.ChangeTheme -> changeTheme(action.mode)
        }
    }

    private fun changeTheme(mode: AppThemeMode) {
        viewModelScope.launch {
            changeThemeUseCase(mode)
                .onFailure {
                    _events.send(SettingsEvents.Error(it.asUiText()))
                }
        }
    }
}