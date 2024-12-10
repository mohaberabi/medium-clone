package com.mohaberabi.tatbeeq.features.detail.domain.source.local

import com.mohaberabi.tatbeeq.core.domain.model.ArticleDetailModel
import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError

interface ArticleDetailLocalDataSource {


    suspend fun insertArticleDetail(
        detail: ArticleDetailModel
    )

    suspend fun getArticleDetail(
        id: String,
    ): ArticleDetailModel?
}