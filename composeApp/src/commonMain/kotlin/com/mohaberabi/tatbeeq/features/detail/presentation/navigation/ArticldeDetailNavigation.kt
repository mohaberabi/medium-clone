package com.mohaberabi.tatbeeq.features.detail.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import androidx.navigation.toRoute
import com.mohaberabi.tatbeeq.app.navigation.root.AppDeepLinkRoutes
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.util.extensions.decode
import com.mohaberabi.tatbeeq.core.util.extensions.encode
import com.mohaberabi.tatbeeq.core.util.extensions.serializableType
import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.ArticleDetailRoute.Companion.typeMap
import com.mohaberabi.tatbeeq.features.detail.presentation.screen.ArticleDetailScreenRoot
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf


@Serializable
data class ArticleDetailArgs(
    val mdPath: String = "",
    val pdfPath: String = "",
)

@Serializable
data class ArticleDetailRoute(
    val args: ArticleDetailArgs
) {
    companion object {
        val typeMap = mapOf(typeOf<ArticleDetailArgs>() to serializableType<ArticleDetailArgs>())
    }
}

fun NavGraphBuilder.articleDetailScreen(
    rootNavController: NavHostController,
) = composable<ArticleDetailRoute>(
    typeMap = typeMap,
    deepLinks = listOf(
        navDeepLink<ArticleDetailRoute>(
            basePath = AppDeepLinkRoutes.ArticleDetail.deepLinkId,
            typeMap = typeMap,
        )
    )
) {
    val route = it.toRoute<ArticleDetailRoute>()
    ArticleDetailScreenRoot(
        args = route.args,
        onBackClick = {
            rootNavController.popBackStack()
        }
    )
}

fun NavHostController.goToArticleDetail(
    args: ArticleDetailArgs,
) = navigate(ArticleDetailRoute(args))