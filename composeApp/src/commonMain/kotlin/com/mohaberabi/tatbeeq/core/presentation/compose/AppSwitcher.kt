package com.mohaberabi.tatbeeq.core.presentation.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing


@Composable
fun <T> AppSwitcher(
    modifier: Modifier = Modifier,
    items: List<T>,
    current: T,
    label: @Composable (T) -> String,
) {

    val theme = MaterialTheme
    Row(
        modifier = modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround,
    ) {

        items.forEach { item ->
            val selected = item == current

            val weight = if (selected) {
                FontWeight.SemiBold
            } else {
                FontWeight.Normal
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .clip(RoundedCornerShape(Spacing.md))
                    .background(
                        if (selected) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.background
                    )
            ) {
                Text(
                    label(item),
                    style = theme.typography.bodyLarge.copy(
                        fontWeight = weight,
                        color = if (selected) theme.colorScheme.onPrimary
                        else theme.typography.headlineMedium.color
                    ),
                    modifier = Modifier.padding(Spacing.md)
                )
            }

        }
    }
}

