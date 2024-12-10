package com.mohaberabi.tatbeeq.features.saved.viewmodel

import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel

sealed interface SavedArticleState {


    data class Done(val articles: List<ArticleModel>) : SavedArticleState


    data object Loading : SavedArticleState
    data object Error : SavedArticleState
}