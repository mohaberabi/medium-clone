package com.mohaberabi.tatbeeq.features.home.presentation.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing
import com.myfitnessbag.order.core.presentation.compose.ext.shimmerEffect


@Composable
fun ArticleShimmer(
    modifier: Modifier = Modifier,
) {


    Row(
        modifier = modifier.fillMaxWidth()
            .padding(bottom = Spacing.md),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier.size(85.dp).clip(
                RoundedCornerShape(Spacing.md)
            ).shimmerEffect()
        )
        Spacer(Modifier.width(Spacing.sm))
        Column(
            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
        ) {
            Box(
                modifier = Modifier.width(226.dp)
                    .height(20.dp)
                    .shimmerEffect()
            )
            Spacer(Modifier.height(Spacing.lg))

            Box(
                modifier = Modifier.width(165.dp).height(12.dp).shimmerEffect()
            )
        }
    }

}

@Composable
fun ArticleLoader(
) {
    LazyColumn(
        userScrollEnabled = false,
        modifier = Modifier.fillMaxSize()
            .padding(Spacing.lg)
    ) {

        items(10) {
            ArticleShimmer()
        }

    }

}