package com.mohaberabi.tatbeeq.features.detail.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.tatbeeq.core.util.asUiText
import com.mohaberabi.tatbeeq.core.util.onFailure
import com.mohaberabi.tatbeeq.core.util.onSuccess
import com.mohaberabi.tatbeeq.features.detail.domain.usecase.DownloadArticleMdUseCase
import com.mohaberabi.tatbeeq.features.detail.domain.usecase.GenerateArticlePdfUseCase
import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.ArticleDetailArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ArticleDetailViewModel(
    private val downloadArticleMdUseCase: DownloadArticleMdUseCase,
    private val generateArticlePdf: GenerateArticlePdfUseCase,
) : ViewModel() {


    private val _state = MutableStateFlow(ArticleDetailState())

    val state = _state.asStateFlow()


    fun onAction(action: ArticleDetailActions) {
        when (action) {
            is ArticleDetailActions.ArticleArgsChanged -> getMd(action.articleArgs)
            ArticleDetailActions.GeneratePdf -> generatePdf()
        }
    }


    private fun generatePdf() {
        viewModelScope.launch {
            generateArticlePdf(_state.value.articleArgs.pdfPath)
        }
    }

    private fun getMd(args: ArticleDetailArgs) {
        _state.update { it.copy(status = ArticleDetailStatus.Loading, articleArgs = args) }
        viewModelScope.launch {
            downloadArticleMdUseCase(args.mdPath)
                .onFailure { err ->
                    _state.update {
                        it.copy(
                            status = ArticleDetailStatus.Error,
                            error = err.asUiText()
                        )
                    }

                }.onSuccess { md ->
                    _state.update {
                        it.copy(
                            status = ArticleDetailStatus.Populated,
                            articleMd = md
                        )
                    }
                }
        }
    }
}