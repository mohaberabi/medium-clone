package com.mohaberabi.tatbeeq.core.data.repository

import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.domain.repository.ArticleRepository
import com.mohaberabi.tatbeeq.core.domain.source.local.ArticleLocalDataSource
import com.mohaberabi.tatbeeq.core.domain.source.remote.ArticleRemoteDataSource
import com.mohaberabi.tatbeeq.core.util.AppResult
import com.mohaberabi.tatbeeq.core.util.EmptyDataResult
import com.mohaberabi.tatbeeq.core.util.error.AppError
import com.mohaberabi.tatbeeq.core.util.error.ErrorModel
import com.mohaberabi.tatbeeq.core.util.handleAppResult

import kotlinx.coroutines.flow.Flow

class OfflineFirstArticleRepository(
    private val articleRemoteDataSource: ArticleRemoteDataSource,
    private val articleLocalDataSource: ArticleLocalDataSource
) : ArticleRepository {
    override fun getArticles(
    ): Flow<List<ArticleModel>> = articleLocalDataSource.getAllArticles()

    override fun getSavedArticles(
    ): Flow<List<ArticleModel>> = articleLocalDataSource.getSavedArticles()


    override suspend fun toggleSavedArticle(
        id: String,
    ): EmptyDataResult<AppError> = handleAppResult {
        articleLocalDataSource.updateSavedStatus(id)
    }

    override suspend fun getAndSaveArticles(
        limit: Int,
        offset: Int
    ): EmptyDataResult<AppError> = handleAppResult {
        val remote = articleRemoteDataSource.getArticles(
            limit = limit,
            offset = offset
        )
        articleLocalDataSource.upsertArticles(articles = remote)
    }

    override suspend fun searchArticles(query: String): AppResult<List<ArticleModel>, ErrorModel> {
        return if (query.isEmpty()) AppResult.Done(listOf()) else handleAppResult {
            articleRemoteDataSource.searchArticles(query)
        }
    }

    override fun getSavedArticleIds(): Flow<Set<String>> =
        articleLocalDataSource.getSavedArticlesIds()

}