package com.mohaberabi.tatbeeq.features.detail.data.di

import com.mohaberabi.tatbeeq.core.data.database.AppDatabase
import com.mohaberabi.tatbeeq.core.data.source.local.RoomArticleDetailLocalDataSource
import com.mohaberabi.tatbeeq.features.detail.data.OfflineFirstArticleDetailRepository
import com.mohaberabi.tatbeeq.features.detail.domain.repository.ArticleDetailRepository
import com.mohaberabi.tatbeeq.features.detail.domain.source.local.ArticleDetailLocalDataSource
import com.mohaberabi.tatbeeq.features.detail.domain.usecase.DownloadArticleMdUseCase
import com.mohaberabi.tatbeeq.features.detail.domain.usecase.GenerateArticlePdfUseCase
import com.mohaberabi.tatbeeq.features.detail.presentation.viewmodel.ArticleDetailViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val articleDetailModule = module {


    single<ArticleDetailLocalDataSource> {
        RoomArticleDetailLocalDataSource(
            get<AppDatabase>().articleDetailDao(),
            get(),
        )

    }
    single<ArticleDetailRepository> {
        OfflineFirstArticleDetailRepository(
            get(),
            get(),
            get()
        )
    }
    single {
        DownloadArticleMdUseCase(get())
    }
    single {
        GenerateArticlePdfUseCase(get())
    }
    viewModelOf(::ArticleDetailViewModel)
}