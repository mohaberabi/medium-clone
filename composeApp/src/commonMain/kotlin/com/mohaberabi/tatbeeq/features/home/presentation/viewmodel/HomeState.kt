package com.mohaberabi.tatbeeq.features.home.presentation.viewmodel

import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.util.UiText


enum class HomeStatus {
    Initial,
    Loading,
    Error,
    Populated
}

data class HomeState(
    val status: HomeStatus = HomeStatus.Initial,
    val articles: List<ArticleModel> = listOf(),
    val error: UiText = UiText.Empty
)