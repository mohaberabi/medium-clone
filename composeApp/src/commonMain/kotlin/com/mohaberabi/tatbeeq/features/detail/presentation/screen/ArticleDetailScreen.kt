package com.mohaberabi.tatbeeq.features.detail.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.tatbeeq.core.presentation.compose.AppLoader
import com.mohaberabi.tatbeeq.core.presentation.compose.AppMdText
import com.mohaberabi.tatbeeq.core.presentation.compose.AppScaffold
import com.mohaberabi.tatbeeq.core.presentation.compose.MainAppBar
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing
import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.ArticleDetailArgs
import com.mohaberabi.tatbeeq.features.detail.presentation.viewmodel.ArticleDetailActions
import com.mohaberabi.tatbeeq.features.detail.presentation.viewmodel.ArticleDetailState
import com.mohaberabi.tatbeeq.features.detail.presentation.viewmodel.ArticleDetailStatus
import com.mohaberabi.tatbeeq.features.detail.presentation.viewmodel.ArticleDetailViewModel
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun ArticleDetailScreenRoot(
    args: ArticleDetailArgs,
    viewmodel: ArticleDetailViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {

    LaunchedEffect(
        Unit,
    ) {
        viewmodel.onAction(ArticleDetailActions.ArticleArgsChanged(args))
    }
    val state by viewmodel.state.collectAsStateWithLifecycle()
    
    ArticleDetailScreen(
        state = state,
        onBackClick = onBackClick,
        onAction = viewmodel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArticleDetailScreen(
    modifier: Modifier = Modifier,
    state: ArticleDetailState,
    onBackClick: () -> Unit,
    onAction: (ArticleDetailActions) -> Unit,
) {

    AppScaffold(
        topAppBar = {
            MainAppBar(
                actions = {
                    IconButton(
                        onClick = { onAction(ArticleDetailActions.GeneratePdf) },
                    ) {
                        Icon(
                            Icons.Default.Share, null,
                        )
                    }
                },
                isCenter = false,
                showBackButton = true,
                onBackClick = onBackClick
            )
        }
    ) { padding ->

        Box(
            modifier.fillMaxSize()
                .padding(Spacing.lg)
                .padding(top = padding.calculateTopPadding())
        ) {
            when (state.status) {
                ArticleDetailStatus.Error -> Text(state.error.asString())
                ArticleDetailStatus.Populated -> SelectionContainer {
                    AppMdText(state.articleMd)
                }

                else -> AppLoader()
            }
        }

    }


}