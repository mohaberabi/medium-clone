package com.mohaberabi.tatbeeq.features.search.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.presentation.compose.AppLoader
import com.mohaberabi.tatbeeq.core.presentation.compose.ArticleLazyColumn
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing
import com.mohaberabi.tatbeeq.features.search.presentation.viewmodel.SearchActions
import com.mohaberabi.tatbeeq.features.search.presentation.viewmodel.SearchState
import com.mohaberabi.tatbeeq.features.search.presentation.viewmodel.SearchStatus
import com.mohaberabi.tatbeeq.features.search.presentation.viewmodel.SearchViewModel
import org.koin.compose.viewmodel.koinNavViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun SearchScreenRoot(
    viewmodel: SearchViewModel = koinViewModel(),
    onClickArticle: (ArticleModel) -> Unit
) {


    val state by viewmodel.state.collectAsStateWithLifecycle()
    SearchScreen(
        onAction = viewmodel::onAction,
        onArticleClick = onClickArticle,
        state = state
    )

}


@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    onAction: (SearchActions) -> Unit,
    state: SearchState,
    onArticleClick: (ArticleModel) -> Unit,
) {


    Column(
        modifier = modifier.fillMaxSize()
    ) {
        TextField(
            colors = TextFieldDefaults.colors(
                disabledIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                unfocusedContainerColor = Color.DarkGray,
                disabledContainerColor = Color.Transparent,
                focusedContainerColor = Color.DarkGray,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = Spacing.md)
                .clip(RoundedCornerShape(Spacing.sm)),
            prefix = {
                Icon(
                    imageVector = Icons.Default.Search,
                    "search",
                    tint = Color.LightGray,
                )
            },
            placeholder = {
                Text(
                    "Search articles..",
                    style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray)
                )
            },
            onValueChange = { onAction(SearchActions.QueryChanged(it)) },
            value = state.searchQuery,
        )

        when (state.status) {
            SearchStatus.Error -> Text("Something went wrong...")
            SearchStatus.Populated -> ArticleLazyColumn(
                articles = state.foundArticles,
                onClick = onArticleClick,
                onSaveClick = {},
                isSaved = { state.savedIds.contains(it.id) }
            )

            SearchStatus.Loading -> AppLoader()
            else -> Unit
        }
    }
}