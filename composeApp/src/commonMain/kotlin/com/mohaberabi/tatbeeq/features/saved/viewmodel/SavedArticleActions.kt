package com.mohaberabi.tatbeeq.features.saved.viewmodel


sealed interface SavedArticleActions {


    data class RemoveSaved(val id: String) : SavedArticleActions
}