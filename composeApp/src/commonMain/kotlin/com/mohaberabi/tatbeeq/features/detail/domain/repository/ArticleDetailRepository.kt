package com.mohaberabi.tatbeeq.features.detail.domain.repository

import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import com.mohaberabi.tatbeeq.core.util.error.ErrorModel

interface ArticleDetailRepository {
    suspend fun getArticleMarkdown(
        fileName: String,
    ): AppResult<String, ErrorModel>


    suspend fun generateArticlePdf(
        pdfPath: String,
    ): EmptyDataResult<ErrorModel>
}