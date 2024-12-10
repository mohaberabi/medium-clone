package com.mohaberabi.tatbeeq.core.domain.repository

import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import com.mohaberabi.tatbeeq.core.util.error.ErrorModel
import kotlinx.coroutines.flow.Flow

interface ArticleRepository {


    fun getArticles(): Flow<List<ArticleModel>>
    fun getSavedArticles(): Flow<List<ArticleModel>>
    suspend fun toggleSavedArticle(
        id: String,
    ): EmptyDataResult<AppError>

    suspend fun getAndSaveArticles(
        limit: Int = 10,
        offset: Int = 0
    ): EmptyDataResult<AppError>

    suspend fun searchArticles(
        query: String
    ): AppResult<List<ArticleModel>, ErrorModel>

    fun getSavedArticleIds(): Flow<Set<String>>
}