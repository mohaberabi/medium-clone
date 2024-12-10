package com.mohaberabi.tatbeeq.features.search.presentation.viewmodel


sealed interface SearchActions {
    data class QueryChanged(val query: String) : SearchActions
}