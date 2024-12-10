package com.mohaberabi.tatbeeq.features.search.presentation.viewmodel

import androidx.compose.runtime.Stable
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel


enum class SearchStatus {
    Initial,
    Loading,
    Error,
    Populated,
}

@Stable
data class SearchState(
    val status: SearchStatus = SearchStatus.Initial,
    val foundArticles: List<ArticleModel> = listOf(),
    val searchQuery: String = "",
    val savedIds: Set<String> = setOf()
)
