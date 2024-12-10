package com.mohaberabi.tatbeeq.core.domain.usecase.article

import com.mohaberabi.tatbeeq.core.domain.repository.ArticleRepository

class SearchArticleUseCase(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(query: String) = articleRepository.searchArticles(query)
}