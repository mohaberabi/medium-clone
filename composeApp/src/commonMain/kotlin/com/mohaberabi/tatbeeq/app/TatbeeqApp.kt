package com.mohaberabi.tatbeeq.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalWindowInfo
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import coil3.disk.DiskCache
import coil3.memory.MemoryCache
import coil3.request.CachePolicy
import coil3.request.crossfade
import coil3.util.DebugLogger
import com.mohaberabi.tatbeeq.app.navigation.root.RootNavHost
import com.mohaberabi.tatbeeq.app.viewmodel.MainViewModel
import com.mohaberabi.tatbeeq.core.data.database.AppDatabase
import com.mohaberabi.tatbeeq.core.presentation.compose.AppScaffold
import com.mohaberabi.tatbeeq.core.presentation.compose.EventCollector
import com.mohaberabi.tatbeeq.core.presentation.design.AppTheme
import com.mohaberabi.tatbeeq.platform_module.rememberAppLocale
import kotlinx.coroutines.launch
import okio.FileSystem
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


@Composable
fun TatbeeqApp(
    viewmodel: MainViewModel = koinViewModel(),
    deepLinkRoute: Any? = null
) {


    val mainState by viewmodel.state.collectAsStateWithLifecycle()
    val currentLanguage = rememberAppLocale()
    val tatbeeqState = rememberTatbeeqState()
    val rootNavController = tatbeeqState.rootNavController
    setSingletonImageLoaderFactory { context ->
        getAsyncImageLoader(context)
    }
    AppCompositionLocals(
        currentLanguage,
        mainState.mode,
    ) {
        EventCollector(
            LocalSnackBarController.current.events,
        ) { event ->
            val message = when (event) {
                is SnackBarEvents.Message -> event.message
                is SnackBarEvents.UiTextMessage -> event.message.getAsString()
            }
            tatbeeqState.coroutineScope.launch {
                tatbeeqState.snackBarHostState.showSnackbar(message)
            }
        }

        if (mainState.didLoad) {
            AppTheme(
                lang = currentLanguage,
                themeMode = mainState.mode
            ) {
                AppScaffold(
                    snackBarHostState = tatbeeqState.snackBarHostState,
                ) {
                    RootNavHost(
                        deepLinkRoute = deepLinkRoute,
                        rootNavController = rootNavController
                    )
                }

            }
        } else {
            AppScaffold { }
        }
    }

}

private fun getAsyncImageLoader(
    context: PlatformContext,
) =
    ImageLoader.Builder(context).memoryCachePolicy(CachePolicy.ENABLED).memoryCache {
        MemoryCache.Builder().maxSizePercent(context, 0.3).strongReferencesEnabled(true).build()
    }.diskCachePolicy(CachePolicy.ENABLED).networkCachePolicy(CachePolicy.ENABLED).diskCache {
        newDiskCache()
    }.crossfade(true).logger(DebugLogger()).build()

private fun newDiskCache(): DiskCache {
    return DiskCache.Builder().directory(FileSystem.SYSTEM_TEMPORARY_DIRECTORY / "tatbeeqImgCache")
        .maxSizeBytes(1024L * 1024 * 1024)
        .build()
}