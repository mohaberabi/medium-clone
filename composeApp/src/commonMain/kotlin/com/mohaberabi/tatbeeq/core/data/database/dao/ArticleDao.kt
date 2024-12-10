package com.mohaberabi.tatbeeq.core.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.tatbeeq.core.data.database.entity.ArticleEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface ArticleDao {


    @Query("SELECT * FROM article")
    fun getAllArticles(): Flow<List<ArticleEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun upsertArticles(articles: List<ArticleEntity>)

    @Query("UPDATE article SET saved = CASE WHEN saved = 1 THEN 0 ELSE 1 END WHERE id = :id")
    suspend fun updateSavedStatus(id: String)

    @Query("SELECT * FROM article WHERE saved = 1")
    fun getSavedArticlesFlow(): Flow<List<ArticleEntity>>

    @Query("SELECT id FROM article WHERE saved = 1")
    fun getSavedArticleIds(): Flow<List<String>>
}
