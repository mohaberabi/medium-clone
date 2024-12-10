package com.mohaberabi.tatbeeq.features.detail.domain.usecase

import com.mohaberabi.tatbeeq.features.detail.domain.repository.ArticleDetailRepository

class GenerateArticlePdfUseCase(
    private val articleDetailRepository: ArticleDetailRepository,
) {


    suspend operator fun invoke(pdfPath: String) =
        articleDetailRepository.generateArticlePdf(pdfPath)
}