package com.mohaberabi.tatbeeq.core.presentation.compose


import androidx.compose.foundation.layout.size
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.NavigationBarItemDefaults
import com.mohaberabi.tatbeeq.app.navigation.toplevel.TopLevelBottomItem

import org.jetbrains.compose.resources.painterResource


@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    top: TopLevelBottomItem,
    onClick: (TopLevelBottomItem) -> Unit = {},
) {
    BottomAppBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
    ) {
        TopLevelBottomItem.entries.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.primary,
                    indicatorColor = Color.Transparent,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = MaterialTheme.colorScheme.primary,
                ),
                selected = top == item,
                onClick = { onClick(item) },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
            )
        }
    }
}

