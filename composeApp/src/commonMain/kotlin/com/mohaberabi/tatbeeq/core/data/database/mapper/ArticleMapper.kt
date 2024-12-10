package com.mohaberabi.tatbeeq.core.data.database.mapper

import com.mohaberabi.tatbeeq.core.data.database.entity.ArticleDetailEntity
import com.mohaberabi.tatbeeq.core.data.database.entity.ArticleEntity
import com.mohaberabi.tatbeeq.core.domain.model.ArticleDetailModel
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel


fun ArticleDetailEntity.toArticle() = ArticleDetailModel(id, markdown)
fun ArticleDetailModel.toArticleEntity() = ArticleDetailEntity(id, markdown)
fun ArticleModel.toArticleEntity() = ArticleEntity(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    markdownPath = markdownPath,
    createdAt = createdAt,
    pdfPath = pdfPath,
)

fun ArticleEntity.toArticleModel() = ArticleModel(
    id = id,
    title = title,
    description = description,
    imageUrl = imageUrl,
    markdownPath = markdownPath,
    createdAt = createdAt,
    pdfPath = pdfPath,
    saved = saved
)