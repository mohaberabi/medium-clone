package com.mohaberabi.tatbeeq.core.domain.usecase.article

import com.mohaberabi.tatbeeq.core.domain.repository.ArticleRepository

class GetSavedArticlesUseCase(
    private val articleRepository: ArticleRepository
) {
    operator fun invoke() = articleRepository.getSavedArticles()

}