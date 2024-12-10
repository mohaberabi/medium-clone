package com.mohaberabi.tatbeeq.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("articleDetail")
data class ArticleDetailEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val markdown: String
)