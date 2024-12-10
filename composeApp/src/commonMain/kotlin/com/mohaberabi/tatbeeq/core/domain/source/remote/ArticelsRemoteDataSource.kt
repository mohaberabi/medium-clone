package com.mohaberabi.tatbeeq.core.domain.source.remote

import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.error.RemoteError

interface ArticleRemoteDataSource {
    suspend fun getArticles(
        limit: Int = 10,
        offset: Int = 0
    ): List<ArticleModel>


    suspend fun searchArticles(
        query: String
    ): List<ArticleModel>
}