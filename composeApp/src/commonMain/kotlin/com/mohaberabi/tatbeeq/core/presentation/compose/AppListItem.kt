package com.mohaberabi.tatbeeq.core.presentation.compose

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


@Composable
fun AppListItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    label: StringResource,
    icon: ImageVector? = null,
    showArrow: Boolean = false,
    supportLabel: String? = null
) {
    ListItem(
        modifier = modifier
            .clickable { onClick() },
        leadingContent =
        icon?.let {
            {
                Icon(
                    imageVector = icon, contentDescription = "",
                    modifier = Modifier.size(22.dp)
                )
            }
        },
        colors = ListItemDefaults.colors(
            containerColor = Color.Transparent,
            leadingIconColor = Color.Gray,
        ),
        supportingContent = {
            supportLabel?.let {
                Text(
                    it, style = MaterialTheme.typography.bodySmall.copy(color = Color.LightGray),
                    modifier = Modifier.padding(top = Spacing.sm)
                )
            }
        },
        headlineContent = {
            Text(
                stringResource(label),
                style = MaterialTheme.typography.titleMedium
            )
        },
        trailingContent = {
            if (showArrow) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.ArrowForward,
                    contentDescription = "",
                    modifier = Modifier.size(20.dp)
                )
            }

        }
    )
}
