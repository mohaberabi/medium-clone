package com.mohaberabi.tatbeeq

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.mohaberabi.tatbeeq.app.TatbeeqApp
import com.mohaberabi.tatbeeq.app.navigation.root.AppDeepLinkRoutes
import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.ArticleDetailArgs
import com.mohaberabi.tatbeeq.features.detail.presentation.navigation.ArticleDetailRoute

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var deepLinkRoute by remember {
                mutableStateOf<Any?>(null)
            }
            intent?.data?.let { uri ->
                Log.d("uri", uri.path.toString())
                deepLinkRoute = extractDeepLinkArguments(uri)

            }
            TatbeeqApp(
                deepLinkRoute = deepLinkRoute
            )
        }
        // adb shell am start -W -a android.intent.action.VIEW -d "tatbeeq-app://article_detail?mdPath=test2.md&pdfPath=test.pdf" com.mohaberabi.tatbeeq

    }

    private fun extractDeepLinkArguments(uri: Uri?): Any? {
        return when {
            uri.toString().contains(AppDeepLinkRoutes.ArticleDetail.deepLinkId) -> {
                val mdPath = uri?.getQueryParameter("mdPath") ?: ""
                val pdfPath = uri?.getQueryParameter("pdfPath") ?: ""
                ArticleDetailRoute(ArticleDetailArgs(mdPath, pdfPath))
            }

            else -> null
        }
    }
}

