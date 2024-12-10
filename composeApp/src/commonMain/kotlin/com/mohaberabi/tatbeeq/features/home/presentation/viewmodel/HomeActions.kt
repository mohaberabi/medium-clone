package com.mohaberabi.tatbeeq.features.home.presentation.viewmodel

sealed interface HomeActions {


    data class ToggleSaved(
        val id: String
    ) : HomeActions
}