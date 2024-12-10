package com.mohaberabi.tatbeeq.core.data.source.remote

import com.mohaberabi.tatbeeq.core.data.network.CommonParams
import com.mohaberabi.tatbeeq.core.data.network.EndPoints
import com.mohaberabi.tatbeeq.core.data.network.dto.ArticleDto
import com.mohaberabi.tatbeeq.core.data.network.dto.toArticle
import com.mohaberabi.tatbeeq.core.data.network.tryNetworkRequest
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.domain.source.remote.ArticleRemoteDataSource

import com.mohaberabi.tatbeeq.platform_module.DispatchersProvider
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Columns
import io.github.jan.supabase.postgrest.query.Order
import io.github.jan.supabase.postgrest.query.filter.TextSearchType
import kotlinx.coroutines.withContext

class SupabaseArticeRemoteDataSource(
    private val dispatchers: DispatchersProvider,
    private val client: SupabaseClient
) : ArticleRemoteDataSource {
    override suspend fun getArticles(
        limit: Int,
        offset: Int,
    ): List<ArticleModel> {
        return withContext(dispatchers.io) {
            tryNetworkRequest {
                val response = client
                    .from(EndPoints.ARTICLES)
                    .select {
                        order(CommonParams.CREATED_AT, Order.DESCENDING)
                        limit(limit.toLong())
                        range(offset.toLong(), (offset + limit - 1).toLong())
                    }
                    .decodeList<ArticleDto>()
                response.map { it.toArticle() }
            }
        }
    }

    override suspend fun searchArticles(
        query: String
    ): List<ArticleModel> {
        return withContext(dispatchers.io) {
            tryNetworkRequest {
                val response = client.from(EndPoints.ARTICLES)
                    .select {
                        filter {
                            textSearch(
                                column = "title",
                                query = query,
                                config = "english",
                                textSearchType = TextSearchType.WEBSEARCH
                            )
                        }
                    }.decodeList<ArticleDto>()
                response.map { it.toArticle() }
            }
        }
    }
}