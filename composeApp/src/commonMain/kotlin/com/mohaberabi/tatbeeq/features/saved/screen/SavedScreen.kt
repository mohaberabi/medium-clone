package com.mohaberabi.tatbeeq.features.saved.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.tatbeeq.app.LocalSnackBarController
import com.mohaberabi.tatbeeq.app.SnackBarEvents
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.presentation.compose.ArticleLazyColumn
import com.mohaberabi.tatbeeq.core.presentation.compose.EventCollector
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing
import com.mohaberabi.tatbeeq.features.home.presentation.compose.ArticleLoader
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeActions
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeEvents
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeState
import com.mohaberabi.tatbeeq.features.saved.viewmodel.SavedArticleActions
import com.mohaberabi.tatbeeq.features.saved.viewmodel.SavedArticleEvents
import com.mohaberabi.tatbeeq.features.saved.viewmodel.SavedArticleState
import com.mohaberabi.tatbeeq.features.saved.viewmodel.SavedViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SavedScreenRoot(
    viewmodel: SavedViewModel = koinViewModel(),
    onArticleClick: (ArticleModel) -> Unit
) {


    val state by viewmodel.state.collectAsStateWithLifecycle()
    val localSnackBar = LocalSnackBarController.current

    EventCollector(
        viewmodel.events
    ) { event ->
        when (event) {
            is SavedArticleEvents.Error -> localSnackBar.sendEvent(
                SnackBarEvents.UiTextMessage(
                    event.error
                )
            )
        }
    }
    SavedScreen(
        state = state,
        onAction = viewmodel::onAction,
        onArticleClick = onArticleClick
    )

}

@Composable
fun SavedScreen(
    state: SavedArticleState,
    modifier: Modifier = Modifier,
    onArticleClick: (ArticleModel) -> Unit,
    onAction: (SavedArticleActions) -> Unit,
) {

    Box(
        modifier.fillMaxSize(),
    ) {
        when (state) {

            SavedArticleState.Error -> Text("Something  Went wrong ")
            is SavedArticleState.Done -> ArticleLazyColumn(
                articles = state.articles,
                onClick = { onArticleClick(it) },
                onSaveClick = { onAction(SavedArticleActions.RemoveSaved(it.id)) }
            )

            else -> ArticleLoader()
        }
    }

}