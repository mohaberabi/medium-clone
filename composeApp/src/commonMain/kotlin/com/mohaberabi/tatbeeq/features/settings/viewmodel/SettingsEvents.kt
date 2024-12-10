package com.mohaberabi.tatbeeq.features.settings.viewmodel

import com.mohaberabi.tatbeeq.core.util.UiText


sealed interface SettingsEvents {

    data class Error(val error: UiText) : SettingsEvents
}