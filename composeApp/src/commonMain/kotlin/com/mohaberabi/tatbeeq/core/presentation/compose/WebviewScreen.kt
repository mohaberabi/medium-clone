package com.mohaberabi.tatbeeq.core.presentation.compose

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.mohaberabi.tatbeeq.platform_module.compose.CustomWebView
import kotlinx.serialization.Serializable


@Serializable
data class WebViewRoute(
    val initialUrl: String
)

fun NavController.goToWebView(
    url: String = "https://google.com",
) = navigate(WebViewRoute(url))

fun NavGraphBuilder.webViewScreen(
    rootNavController: NavController
) = composable<WebViewRoute> {
    WebViewScreen(
        it.toRoute<WebViewRoute>().initialUrl,
        onGoBack = {
            rootNavController.navigateUp()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WebViewScreen(
    initialUrl: String,
    onGoBack: () -> Unit,
) {
    AppScaffold(
        topAppBar = {
            MainAppBar(
                onBackClick = onGoBack,
                showBackButton = true
            )
        }
    ) { padding ->
        CustomWebView(
            modifier = Modifier.fillMaxSize().padding(top = padding.calculateTopPadding()),
            initialUrl = initialUrl,
        )

    }

}