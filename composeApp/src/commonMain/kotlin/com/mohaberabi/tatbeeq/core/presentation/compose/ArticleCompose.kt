package com.mohaberabi.tatbeeq.core.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.mohaberabi.tatbeeq.core.domain.model.ArticleModel
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing
import com.mohaberabi.tatbeeq.core.util.extensions.noRippleClickable
import org.jetbrains.compose.resources.painterResource
import tatbeeq.composeapp.generated.resources.Res
import tatbeeq.composeapp.generated.resources.ic_bookmark


@Composable
fun ArticleCompose(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    article: ArticleModel,
    onSave: () -> Unit = {},
    isSaved: Boolean = false
) {


    Row(
        modifier = modifier.fillMaxWidth()
            .padding(bottom = Spacing.md)
            .noRippleClickable(onClick),
        verticalAlignment = Alignment.Top
    ) {
        Column(
            modifier = Modifier.wrapContentHeight()
                .fillMaxWidth(0.8f),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                article.title,
                style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            Spacer(Modifier.height(Spacing.xs))
            Text(
                article.description,
                style = MaterialTheme.typography.labelSmall.copy(color = Color.Gray),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = onSave,
                ) {
                    Icon(
                        painterResource(Res.drawable.ic_bookmark),
                        null,
                        modifier = Modifier.size(18.dp),
                        tint = if (article.saved || isSaved) MaterialTheme.colorScheme.primary
                        else Color.LightGray
                    )
                }
            }

        }
        Spacer(Modifier.width(Spacing.sm))
        CachedImage(
            model = article.imageUrl,
            size = 65.dp,
            borderRadius = 0.dp,
            modifier = Modifier.clip(RoundedCornerShape(Spacing.sm))
        )


    }

}

