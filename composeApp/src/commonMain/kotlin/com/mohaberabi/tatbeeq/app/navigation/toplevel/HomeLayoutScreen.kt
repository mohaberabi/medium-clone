package com.mohaberabi.tatbeeq.app.navigation.toplevel

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mohaberabi.tatbeeq.core.presentation.compose.AppBottomBar
import com.mohaberabi.tatbeeq.core.presentation.compose.AppScaffold
import com.mohaberabi.tatbeeq.core.presentation.compose.MainAppBar
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel


@Composable
fun LayoutScreenRoot(
    modifier: Modifier = Modifier,
    onTopLevelClick: (TopLevelBottomItem) -> Unit,
    viewmodel: TopLevelRouteViewModel = koinViewModel(),
    content: @Composable (PaddingValues) -> Unit,
) {

    val currentRoute by viewmodel.currentRoute.collectAsStateWithLifecycle()
    LayoutScreen(
        modifier = modifier,
        currentRoute = currentRoute,
        onClick = {
            onTopLevelClick(it)
            viewmodel.routeChanged(it)
        },
        content = content
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutScreen(
    modifier: Modifier = Modifier,
    currentRoute: TopLevelBottomItem,
    onClick: (TopLevelBottomItem) -> Unit,
    content: @Composable (PaddingValues) -> Unit,
) {


    AppScaffold(
        topAppBar = {
            MainAppBar(
                isCenter = false,
                titleContent = {
                    Text(
                        stringResource(currentRoute.label),
                        style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
                    )
                },
                showBackButton = false,
                modifier = Modifier.wrapContentHeight(),
            )
        },
        modifier = modifier,
        bottomAppBar = {
            AppBottomBar(
                top = currentRoute,
                onClick = { item ->
                    onClick(item)
                },
            )
        }
    ) { padding ->
        content(padding)
    }
}