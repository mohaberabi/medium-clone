package com.mohaberabi.tatbeeq.core.domain.usecase.article

import com.mohaberabi.tatbeeq.core.domain.repository.ArticleRepository

class GetAndSaveArticleUseCase(
    private val articleRepository: ArticleRepository,
) {

    suspend operator fun invoke(
        limit: Int = 10,
        offset: Int = 0
    ) = articleRepository.getAndSaveArticles(
        limit = limit,
        offset = offset
    )
}