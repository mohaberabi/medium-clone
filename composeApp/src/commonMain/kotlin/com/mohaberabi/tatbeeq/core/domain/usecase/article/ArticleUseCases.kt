package com.mohaberabi.tatbeeq.core.domain.usecase.article


data class ArticleUseCases(

    val getArticles: GetArticlesUseCase,
    val toggleSavedArticle: ToggleSavedArticleUseCase,
    val getAndSaveArticles: GetAndSaveArticleUseCase,
    val getSavedArticle: GetSavedArticlesUseCase,
    val searchArticle: SearchArticleUseCase,
    val getSavedArticleIds: GetSavedArticleIdsUseCase,
)