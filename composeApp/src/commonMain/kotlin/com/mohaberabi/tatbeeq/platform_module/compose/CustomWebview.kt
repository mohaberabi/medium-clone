package com.mohaberabi.tatbeeq.platform_module.compose

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier


typealias QueryParamsMap = Map<String, String?>

@Composable
expect fun CustomWebView(
    modifier: Modifier = Modifier,
    initialUrl: String,
    onLoaded: (String, QueryParamsMap) -> Unit = { _, _ -> },
    onLoading: (String, QueryParamsMap) -> Unit = { _, _ -> },
    isLoading: (Boolean) -> Unit = { },
)

expect fun String.toQueryParams(): QueryParamsMap

