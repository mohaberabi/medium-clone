package com.mohaberabi.tatbeeq.app.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.tatbeeq.core.domain.usecase.GetThemeModeUseCase
import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn


data class MainAppState(
    val mode: AppThemeMode = AppThemeMode.System,
    val didLoad: Boolean = false,
)

class MainViewModel(
    getThemeModeUseCase: GetThemeModeUseCase
) : ViewModel() {
    val state = getThemeModeUseCase().map {
        MainAppState(it, true)
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000L),
        MainAppState()
    )
}