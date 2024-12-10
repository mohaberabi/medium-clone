package com.mohaberabi.tatbeeq.core.data.source.local

import com.mohaberabi.tatbeeq.core.data.database.dao.ArticleDetailDao
import com.mohaberabi.tatbeeq.core.data.database.mapper.toArticle
import com.mohaberabi.tatbeeq.core.data.database.mapper.toArticleEntity
import com.mohaberabi.tatbeeq.core.data.database.tryDataBaseOperation
import com.mohaberabi.tatbeeq.core.domain.model.ArticleDetailModel
import com.mohaberabi.tatbeeq.features.detail.domain.source.local.ArticleDetailLocalDataSource
import com.mohaberabi.tatbeeq.platform_module.DispatchersProvider
import kotlinx.coroutines.withContext

class RoomArticleDetailLocalDataSource(
    private val articleDetailDao: ArticleDetailDao,
    private val disaptchers: DispatchersProvider
) : ArticleDetailLocalDataSource {

    override suspend fun insertArticleDetail(
        detail: ArticleDetailModel,
    ) {
        return withContext(disaptchers.io) {
            tryDataBaseOperation {
                articleDetailDao.upsertArticleDetail(detail.toArticleEntity())
            }
        }
    }

    override suspend fun getArticleDetail(
        id: String,
    ): ArticleDetailModel? {
        return withContext(disaptchers.io) {
            tryDataBaseOperation {
                articleDetailDao.getArticleDetail(id)?.toArticle()
            }
        }
    }
}