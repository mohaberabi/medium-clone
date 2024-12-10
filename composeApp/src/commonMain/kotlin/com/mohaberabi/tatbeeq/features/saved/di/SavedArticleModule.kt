package com.mohaberabi.tatbeeq.features.saved.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.mohaberabi.tatbeeq.features.saved.viewmodel.SavedViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val savedArticleModule = module {


    viewModelOf(::SavedViewModel)
}