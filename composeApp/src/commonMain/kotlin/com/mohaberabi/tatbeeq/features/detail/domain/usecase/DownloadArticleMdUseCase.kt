package com.mohaberabi.tatbeeq.features.detail.domain.usecase

import com.mohaberabi.tatbeeq.features.detail.domain.repository.ArticleDetailRepository

class DownloadArticleMdUseCase(
    private val articleDetailRepository: ArticleDetailRepository,
) {
    suspend operator fun invoke(fileName: String) =
        articleDetailRepository.getArticleMarkdown(fileName)
}