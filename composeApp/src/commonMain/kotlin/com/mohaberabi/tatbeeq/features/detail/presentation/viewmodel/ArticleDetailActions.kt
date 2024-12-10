package com.mohaberabi.tatbeeq.features.detail.presentation.viewmodel

import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.ArticleDetailArgs


sealed interface ArticleDetailActions {


    data object GeneratePdf : ArticleDetailActions
    data class ArticleArgsChanged(
        val articleArgs: ArticleDetailArgs,
    ) : ArticleDetailActions
}