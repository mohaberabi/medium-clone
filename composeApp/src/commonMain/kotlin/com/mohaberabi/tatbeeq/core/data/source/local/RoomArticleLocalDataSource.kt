package com.mohaberabi.tatbeeq.core.data.source.local

import com.mohaberabi.tatbeeq.core.data.database.dao.ArticleDao
import com.mohaberabi.tatbeeq.core.data.database.mapper.toArticleEntity
import com.mohaberabi.tatbeeq.core.data.database.mapper.toArticleModel
import com.mohaberabi.tatbeeq.core.data.database.tryDataBaseOperation
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.domain.source.local.ArticleLocalDataSource
import com.mohaberabi.tatbeeq.platform_module.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RoomArticleLocalDataSource(
    private val articleDao: ArticleDao,
    private val dispatchers: DispatchersProvider
) : ArticleLocalDataSource {
    override fun getAllArticles(): Flow<List<ArticleModel>> =
        articleDao.getAllArticles().map { list ->
            list.map { it.toArticleModel() }
        }.flowOn(dispatchers.io)

    override suspend fun upsertArticles(
        articles: List<ArticleModel>,
    ) {

        withContext(dispatchers.io) {
            tryDataBaseOperation {
                articleDao.upsertArticles(articles.map {
                    it.toArticleEntity()
                })
            }
        }
    }

    override suspend fun updateSavedStatus(id: String) {
        withContext(dispatchers.io) {
            tryDataBaseOperation {
                articleDao.updateSavedStatus(id)
            }
        }

    }

    override fun getSavedArticles(): Flow<List<ArticleModel>> =
        articleDao.getSavedArticlesFlow().map { list ->
            list.map { it.toArticleModel() }
        }.flowOn(dispatchers.io)

    override fun getSavedArticlesIds(): Flow<Set<String>> =
        articleDao.getSavedArticleIds().map { it.toSet() }
            .distinctUntilChanged()
            .flowOn(dispatchers.io)

}