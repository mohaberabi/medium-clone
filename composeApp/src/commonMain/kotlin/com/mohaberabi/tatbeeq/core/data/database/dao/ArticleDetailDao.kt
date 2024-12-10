package com.mohaberabi.tatbeeq.core.data.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.mohaberabi.tatbeeq.core.data.database.entity.ArticleDetailEntity


@Dao
interface ArticleDetailDao {


    @Upsert
    suspend fun upsertArticleDetail(
        detail: ArticleDetailEntity,
    )

    @Query("SELECT * FROM articleDetail WHERE id =:id")
    suspend fun getArticleDetail(
        id: String,
    ): ArticleDetailEntity?
}