package com.mohaberabi.tatbeeq.features.home.presentation.viewmodel

import com.mohaberabi.tatbeeq.core.util.UiText

sealed interface HomeEvents {


    data class Error(val error: UiText) : HomeEvents
}