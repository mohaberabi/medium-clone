package com.mohaberabi.tatbeeq.core.domain.usecase.article

import com.mohaberabi.tatbeeq.core.domain.repository.ArticleRepository

class ToggleSavedArticleUseCase(
    private val articleRepository: ArticleRepository,
) {


    suspend operator fun invoke(
        id: String,
    ) = articleRepository.toggleSavedArticle(id = id)
}