package com.mohaberabi.tatbeeq.features.home.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.domain.usecase.article.ArticleUseCases
import com.mohaberabi.tatbeeq.core.util.asUiText
import com.mohaberabi.tatbeeq.core.util.onFailure
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {


    private val _state = MutableStateFlow(HomeState())
    val state = _state.onStart {
        getArticles()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5_000),
        HomeState()
    )


    private fun getArticles() {
        articleUseCases.getArticles()
            .onStart {
                loadAndSaveNewArticles()
                _state.update { it.copy(status = HomeStatus.Loading) }
            }.onEach { articls ->
                _state.update {
                    it.copy(
                        status = HomeStatus.Populated,
                        articles = articls
                    )
                }
            }.catch {
                _state.update {
                    it.copy(status = HomeStatus.Error)
                }
            }
            .launchIn(viewModelScope)
    }

    private val _events = Channel<HomeEvents>()
    val events = _events.receiveAsFlow()


    fun onAction(action: HomeActions) {
        when (action) {
            is HomeActions.ToggleSaved -> toggleSavedArticle(action.id)
        }
    }

    private fun toggleSavedArticle(id: String) {
        viewModelScope.launch {
            articleUseCases.toggleSavedArticle(id)
                .onFailure {
                    _events.send(HomeEvents.Error(it.asUiText()))
                }
        }
    }


    private fun loadAndSaveNewArticles() {
        viewModelScope.launch {
            articleUseCases.getAndSaveArticles()
        }
    }

    private fun loadMoreArticles() {
        val offset = _state.value.articles.size
        viewModelScope.launch {
            articleUseCases.getAndSaveArticles(offset = offset)
        }
    }
}