package com.mohaberabi.tatbeeq.core.data.di

import com.mohaberabi.tatbeeq.core.data.database.AppDatabase
import com.mohaberabi.tatbeeq.core.data.repository.OfflineFirstArticleRepository
import com.mohaberabi.tatbeeq.core.data.source.local.RoomArticleLocalDataSource
import com.mohaberabi.tatbeeq.core.data.source.remote.SupabaseArticeRemoteDataSource
import com.mohaberabi.tatbeeq.core.domain.repository.ArticleRepository
import com.mohaberabi.tatbeeq.core.domain.source.local.ArticleLocalDataSource
import com.mohaberabi.tatbeeq.core.domain.source.remote.ArticleRemoteDataSource
import com.mohaberabi.tatbeeq.core.domain.usecase.article.ArticleUseCases
import com.mohaberabi.tatbeeq.core.domain.usecase.article.GetAndSaveArticleUseCase
import com.mohaberabi.tatbeeq.core.domain.usecase.article.GetArticlesUseCase
import com.mohaberabi.tatbeeq.core.domain.usecase.article.GetSavedArticleIdsUseCase
import com.mohaberabi.tatbeeq.core.domain.usecase.article.GetSavedArticlesUseCase
import com.mohaberabi.tatbeeq.core.domain.usecase.article.SearchArticleUseCase
import com.mohaberabi.tatbeeq.core.domain.usecase.article.ToggleSavedArticleUseCase
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val articlesModule = module {

    single<ArticleLocalDataSource> {
        RoomArticleLocalDataSource(
            articleDao = get<AppDatabase>().articleDao(),
            dispatchers = get()
        )
    }



    single<ArticleRemoteDataSource> {
        SupabaseArticeRemoteDataSource(
            dispatchers = get(),
            client = get(),
        )
    }
    single<ArticleRepository> {
        OfflineFirstArticleRepository(
            articleRemoteDataSource = get(),
            articleLocalDataSource = get()
        )
    }

    single {
        GetArticlesUseCase(get())
    }
    single {
        ToggleSavedArticleUseCase(get())
    }
    single {
        GetAndSaveArticleUseCase(get())
    }
    single {
        GetSavedArticlesUseCase(get())
    }
    single {
        SearchArticleUseCase(get())
    }
    single {
        GetSavedArticleIdsUseCase(get())
    }
    single {
        ArticleUseCases(
            getArticles = get(),
            toggleSavedArticle = get(),
            getAndSaveArticles = get(),
            getSavedArticle = get(),
            searchArticle = get(),
            getSavedArticleIds = get()
        )
    }
    viewModelOf(::HomeViewModel)
}