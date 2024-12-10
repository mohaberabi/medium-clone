package com.mohaberabi.tatbeeq.core.data.network.dto

import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class ArticleDto(
    val id: String,
    val title: String,
    val description: String,
    val imgUrl: String,
    val mdPath: String,
    @SerialName("created_at")
    val createdAt: String,
    val pdfPath: String,
)

fun ArticleDto.toArticle() =
    ArticleModel(
        id = id,
        title = title,
        description = description,
        imageUrl = imgUrl,
        markdownPath = mdPath,
        createdAt = createdAt,
        pdfPath = pdfPath
    )