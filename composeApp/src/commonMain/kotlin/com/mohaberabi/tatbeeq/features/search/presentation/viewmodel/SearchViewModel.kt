package com.mohaberabi.tatbeeq.features.search.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.tatbeeq.core.domain.usecase.article.ArticleUseCases
import com.mohaberabi.tatbeeq.core.util.onFailure
import com.mohaberabi.tatbeeq.core.util.onSuccess
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val articleUseCase: ArticleUseCases
) : ViewModel() {


    private var searchJob: Job? = null


    private val _state = MutableStateFlow(SearchState())
    val state = articleUseCase.getSavedArticleIds()
        .flatMapLatest { savedIds ->
            _state.map { it.copy(savedIds = savedIds) }
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5_000),
            SearchState()
        )


    fun onAction(action: SearchActions) {
        when (action) {
            is SearchActions.QueryChanged -> searchArticles(action.query)
        }
    }

    private fun searchArticles(query: String) {
        _state.update { it.copy(searchQuery = query) }
        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            _state.update { it.copy(status = SearchStatus.Loading) }
            articleUseCase.searchArticle(query)
                .onFailure { _state.update { it.copy(status = SearchStatus.Error) } }
                .onSuccess { artcls ->
                    _state.update {
                        it.copy(
                            status = SearchStatus.Populated,
                            foundArticles = artcls
                        )
                    }
                }
        }
    }
}
