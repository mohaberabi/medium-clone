package com.mohaberabi.tatbeeq.features.search.di

import com.mohaberabi.tatbeeq.features.search.presentation.viewmodel.SearchViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val searchModule = module {


    viewModelOf(::SearchViewModel)
}