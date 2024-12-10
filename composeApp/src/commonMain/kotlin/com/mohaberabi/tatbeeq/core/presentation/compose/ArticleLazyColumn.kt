package com.mohaberabi.tatbeeq.core.presentation.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeActions
import com.mohaberabi.tatbeeq.features.home.presentation.viewmodel.HomeState


@Composable
fun ArticleLazyColumn(
    modifier: Modifier = Modifier,
    articles: List<ArticleModel>,
    onClick: (ArticleModel) -> Unit,
    onSaveClick: (ArticleModel) -> Unit,
    isSaved: (ArticleModel) -> Boolean = { false }
) {


    if (articles.isEmpty()) {
        AppPlaceHolder()
    } else {
        LazyColumn(
            modifier = modifier.padding(Spacing.lg)
        ) {
            items(
                articles,
            ) { article ->
                ArticleCompose(
                    isSaved = isSaved(article),
                    onClick = { onClick(article) },
                    article = article,
                    onSave = { onSaveClick(article) }
                )
            }
        }
    }


}