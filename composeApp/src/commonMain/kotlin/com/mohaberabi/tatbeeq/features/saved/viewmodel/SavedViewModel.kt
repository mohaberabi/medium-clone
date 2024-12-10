package com.mohaberabi.tatbeeq.features.saved.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.domain.usecase.article.ArticleUseCases
import com.mohaberabi.tatbeeq.core.util.asUiText
import com.mohaberabi.tatbeeq.core.util.onFailure
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SavedViewModel(
    private val articleUseCases: ArticleUseCases
) : ViewModel() {


    val state = articleUseCases.getSavedArticle()
        .map<List<ArticleModel>, SavedArticleState> { SavedArticleState.Done(it) }
        .catch { emit(SavedArticleState.Error) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SavedArticleState.Loading
        )

    private val _events = Channel<SavedArticleEvents>()
    val events = _events.receiveAsFlow()


    fun onAction(action: SavedArticleActions) {
        when (action) {
            is SavedArticleActions.RemoveSaved -> removeSaved(action.id)
        }
    }

    private fun removeSaved(id: String) {
        viewModelScope.launch {
            articleUseCases.toggleSavedArticle(id)
                .onFailure {
                    _events.send(SavedArticleEvents.Error(it.asUiText()))
                }
        }
    }

}