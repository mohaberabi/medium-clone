package com.mohaberabi.tatbeeq.core.domain.source.local


import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import kotlinx.coroutines.flow.Flow


interface ArticleLocalDataSource {


    fun getAllArticles(): Flow<List<ArticleModel>>

    suspend fun upsertArticles(articles: List<ArticleModel>)

    suspend fun updateSavedStatus(id: String)

    fun getSavedArticles(): Flow<List<ArticleModel>>


    fun getSavedArticlesIds(): Flow<Set<String>>
}
