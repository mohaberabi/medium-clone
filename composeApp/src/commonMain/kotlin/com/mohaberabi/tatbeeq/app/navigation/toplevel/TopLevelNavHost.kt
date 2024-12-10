package com.mohaberabi.tatbeeq.app.navigation.toplevel

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.presentation.compose.goToWebView
import com.mohaberabi.tatbeeq.core.util.extensions.decode
import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.ArticleDetailArgs
import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.goToArticleDetail
import com.mohaberabi.tatbeeq.features.home.presentation.screen.HomeScreenRoot
import com.mohaberabi.tatbeeq.features.saved.screen.SavedScreenRoot
import com.mohaberabi.tatbeeq.features.search.screen.SearchScreenRoot
import com.mohaberabi.tatbeeq.features.settings.screen.SettingsScreenRoot
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.koin.compose.viewmodel.koinViewModel
import tatbeeq.composeapp.generated.resources.Res
import tatbeeq.composeapp.generated.resources.home
import tatbeeq.composeapp.generated.resources.ic_bookmark
import tatbeeq.composeapp.generated.resources.ic_home
import tatbeeq.composeapp.generated.resources.ic_search
import tatbeeq.composeapp.generated.resources.ic_settings
import tatbeeq.composeapp.generated.resources.saved
import tatbeeq.composeapp.generated.resources.search
import tatbeeq.composeapp.generated.resources.settings


@Serializable
data object HomeLayout

enum class TopLevelBottomItem(
    val icon: DrawableResource,
    val route: TopLevelRoutes,
    val label: StringResource
) {
    Home(Res.drawable.ic_home, TopLevelRoutes.Home, Res.string.home),
    Search(Res.drawable.ic_search, TopLevelRoutes.Search, Res.string.search),
    Saved(Res.drawable.ic_bookmark, TopLevelRoutes.Saved, Res.string.saved),
    Settings(Res.drawable.ic_settings, TopLevelRoutes.Settings, Res.string.settings),

}

@Serializable
sealed interface TopLevelRoutes {

    @Serializable
    data object Home : TopLevelRoutes

    @Serializable

    data object Search : TopLevelRoutes

    @Serializable

    data object Saved : TopLevelRoutes

    @Serializable

    data object Settings : TopLevelRoutes
}


fun NavGraphBuilder.homeLayout(
    rootNavController: NavHostController,
) = composable<HomeLayout> {
    val topLevelNavController = rememberNavController()

    val backStackEntry by
    topLevelNavController.currentBackStackEntryFlow.collectAsStateWithLifecycle(null)
    backStackEntry?.let {
        val asRoute = try {
            it.destination.route.decode<TopLevelRoutes>()
        } catch (e: Exception) {
            println("error:${e}")
        }
        println("AsRoute:${asRoute}")
        println("route:${it.destination.route}")
    }
    LayoutScreenRoot(
        onTopLevelClick = {
            topLevelNavController.navigateTop(it)
        },
    ) { padding ->
        TopLevelNavHost(
            modifier = Modifier.padding(padding),
            topLevelNavController = topLevelNavController,
            rootNavController = rootNavController,
        )
    }
}

@Composable
fun TopLevelNavHost(
    modifier: Modifier = Modifier,
    startRoute: TopLevelRoutes = TopLevelRoutes.Home,
    topLevelNavController: NavHostController,
    rootNavController: NavHostController
) {

    val goToArticle = remember {
        fun(article: ArticleModel) {
            rootNavController.goToArticleDetail(
                ArticleDetailArgs(
                    mdPath = article.markdownPath,
                    pdfPath = article.pdfPath
                )
            )
        }
    }
    NavHost(
        modifier = modifier,
        navController = topLevelNavController,
        startDestination = startRoute
    ) {


        composable<TopLevelRoutes.Home> {
            HomeScreenRoot(
                onArticleClick = goToArticle
            )
        }
        composable<TopLevelRoutes.Saved> {
            SavedScreenRoot(
                onArticleClick = goToArticle

            )
        }
        composable<TopLevelRoutes.Search> {
            SearchScreenRoot(
                onClickArticle = goToArticle
            )
        }
        composable<TopLevelRoutes.Settings> {
            SettingsScreenRoot(
                onOpenWeb = {
                    rootNavController.goToWebView(it)
                }
            )
        }

    }

}

fun NavController.navigateTop(
    item: TopLevelBottomItem,
) =
    navigate(item.route) {
        graph.startDestinationRoute?.let {
            popUpTo(it) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
