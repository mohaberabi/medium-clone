package com.mohaberabi.tatbeeq.features.settings.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohaberabi.tatbeeq.features.settings.viewmodel.SettingsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val settingsModule = module {


    viewModelOf(::SettingsViewModel)
}