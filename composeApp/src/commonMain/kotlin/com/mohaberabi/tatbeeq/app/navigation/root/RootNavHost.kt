package com.mohaberabi.tatbeeq.app.navigation.root

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mohaberabi.tatbeeq.app.navigation.toplevel.HomeLayout
import com.mohaberabi.tatbeeq.app.navigation.toplevel.homeLayout
import com.mohaberabi.tatbeeq.core.presentation.compose.WebViewRoute
import com.mohaberabi.tatbeeq.core.presentation.compose.WebViewScreen
import com.mohaberabi.tatbeeq.core.presentation.compose.webViewScreen
import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.articleDetailScreen
import com.mohaberabi.tatbeeq.features.detail.presentation.viewmodel.ArticleDetailViewModel
import org.koin.compose.viewmodel.koinNavViewModel


@Composable
fun RootNavHost(
    rootNavController: NavHostController,
    startRoute: Any = HomeLayout,
    deepLinkRoute: Any? = null,
) {
    LaunchedEffect(
        deepLinkRoute
    ) {
        deepLinkRoute?.let {
            rootNavController.navigate(it)
        }
    }
    NavHost(
        navController = rootNavController,
        startDestination = startRoute
    ) {
        homeLayout(
            rootNavController = rootNavController,
        )
        articleDetailScreen(
            rootNavController = rootNavController,
        )
        webViewScreen(
            rootNavController = rootNavController
        )
    }


}

