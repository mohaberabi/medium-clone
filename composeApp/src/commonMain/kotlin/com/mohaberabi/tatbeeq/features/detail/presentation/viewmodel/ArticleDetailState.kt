package com.mohaberabi.tatbeeq.features.detail.presentation.viewmodel

import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.util.UiText
import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.ArticleDetailArgs


enum class ArticleDetailStatus {
    Initial,
    Loading,
    Error,
    Populated,
}

data class ArticleDetailState(
    val articleArgs: ArticleDetailArgs = ArticleDetailArgs(),
    val status: ArticleDetailStatus = ArticleDetailStatus.Initial,
    val error: UiText = UiText.Empty,
    val articleMd: String = ""
)
