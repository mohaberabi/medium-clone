package com.mohaberabi.tatbeeq.features.saved.viewmodel

import com.mohaberabi.tatbeeq.core.util.UiText


sealed interface SavedArticleEvents {


    data class Error(val error: UiText) : SavedArticleEvents
}