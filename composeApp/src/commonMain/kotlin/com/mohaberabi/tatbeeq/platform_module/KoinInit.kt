import com.mohaberabi.tatbeeq.core.data.di.appModule
import com.mohaberabi.tatbeeq.core.data.di.articlesModule
import com.mohaberabi.tatbeeq.features.detail.data.di.articleDetailModule
import com.mohaberabi.tatbeeq.features.saved.di.savedArticleModule
import com.mohaberabi.tatbeeq.features.search.di.searchModule
import com.mohaberabi.tatbeeq.features.settings.di.settingsModule
import com.mohaberabi.tatbeeq.platform_module.platformModule
import org.koin.core.module.Module

expect class KoinInit {
    fun init(
        vararg extraModules: Module = arrayOf(
            appModule,
            platformModule,
            articlesModule,
            articleDetailModule,
            settingsModule,
            savedArticleModule,
            searchModule
        )
    )
}

