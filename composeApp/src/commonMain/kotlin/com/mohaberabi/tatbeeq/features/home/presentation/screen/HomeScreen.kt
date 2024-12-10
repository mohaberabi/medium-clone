package com.mohaberabi.tatbeeq.features.home.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.tatbeeq.app.LocalSnackBarController
import com.mohaberabi.tatbeeq.app.SnackBarEvents
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.presentation.compose.ArticleCompose
import com.mohaberabi.tatbeeq.core.presentation.compose.ArticleLazyColumn
import com.mohaberabi.tatbeeq.core.presentation.compose.EventCollector
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing
import com.mohaberabi.tatbeeq.features.home.presentation.compose.ArticleLoader
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeActions
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeEvents
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeState
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeStatus
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun HomeScreenRoot(
    viewmodel: HomeViewModel = koinViewModel(),
    onArticleClick: (ArticleModel) -> Unit,
) {
    val localSnackBar = LocalSnackBarController.current

    EventCollector(
        viewmodel.events
    ) { event ->
        when (event) {
            is HomeEvents.Error -> localSnackBar.sendEvent(
                SnackBarEvents.UiTextMessage(event.error),
            )
        }
    }
    val state by viewmodel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onArticleClick = onArticleClick,
        onAction = viewmodel::onAction
    )
}


@Composable
fun HomeScreen(
    state: HomeState,
    modifier: Modifier = Modifier,
    onArticleClick: (ArticleModel) -> Unit,
    onAction: (HomeActions) -> Unit,
) {

    Box(
        modifier.fillMaxSize(),
    ) {
        when (state.status) {

            HomeStatus.Error -> Text("Something  Went wrong ")
            HomeStatus.Populated -> ArticleLazyColumn(
                articles = state.articles,
                onClick = { onArticleClick(it) },
                onSaveClick = { onAction(HomeActions.ToggleSaved(it.id)) }
            )

            else -> ArticleLoader()
        }
    }

}