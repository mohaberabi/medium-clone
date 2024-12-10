package com.mohaberabi.tatbeeq.core.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable


@Entity("article")
data class ArticleEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val markdownPath: String,
    val createdAt: String,
    val pdfPath: String,
    val saved: Boolean = false,
)



