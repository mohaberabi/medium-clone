package com.mohaberabi.tatbeeq.core.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleModel(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String,
    val markdownPath: String,
    val createdAt: String,
    val pdfPath: String = "",
    val saved: Boolean = false,
)


