package com.mohaberabi.tatbeeq.app

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope

data class TatbeeqState(
    val coroutineScope: CoroutineScope,
    val rootNavController: NavHostController,
    val snackBarHostState: SnackbarHostState
)


@Composable
fun rememberTatbeeqState(
): TatbeeqState {
    val rootNavController = rememberNavController()
    val scope = rememberCoroutineScope()
    return remember(
        rootNavController,
        scope,
    ) {
        TatbeeqState(
            coroutineScope = scope,
            rootNavController = rootNavController,
            snackBarHostState = SnackbarHostState()
        )
    }
}