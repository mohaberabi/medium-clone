package com.mohaberabi.tatbeeq.features.settings.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import com.mohaberabi.tatbeeq.app.LocalAppLanguage
import com.mohaberabi.tatbeeq.app.LocalThemeMode
import com.mohaberabi.tatbeeq.core.presentation.compose.AppBottomSheet
import com.mohaberabi.tatbeeq.core.presentation.compose.AppListItem
import com.mohaberabi.tatbeeq.core.presentation.compose.AppRadio
import com.mohaberabi.tatbeeq.core.presentation.compose.AppSwitcher
import com.mohaberabi.tatbeeq.core.presentation.compose.EventCollector
import com.mohaberabi.tatbeeq.core.presentation.design.AppThemeMode
import com.mohaberabi.tatbeeq.core.presentation.design.Spacing
import com.mohaberabi.tatbeeq.features.settings.viewmodel.SettingsActions
import com.mohaberabi.tatbeeq.features.settings.viewmodel.SettingsEvents
import com.mohaberabi.tatbeeq.features.settings.viewmodel.SettingsViewModel
import com.mohaberabi.tatbeeq.platform_module.rememberPackageManager
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import rememberUrlLauncher
import tatbeeq.composeapp.generated.resources.Res
import tatbeeq.composeapp.generated.resources.app_version
import tatbeeq.composeapp.generated.resources.appearance
import tatbeeq.composeapp.generated.resources.lang
import tatbeeq.composeapp.generated.resources.leave_review
import tatbeeq.composeapp.generated.resources.policy
import tatbeeq.composeapp.generated.resources.send_feedback
import tatbeeq.composeapp.generated.resources.settings
import tatbeeq.composeapp.generated.resources.terms_use


@Composable
fun SettingsScreenRoot(
    viewModel: SettingsViewModel = koinViewModel(),
    onOpenWeb: (String) -> Unit,

    ) {


    EventCollector(
        viewModel.events
    ) { event ->
        when (event) {
            is SettingsEvents.Error -> Unit
        }
    }

    SettingsScreen(
        onAction = viewModel::onAction,
        onOpenWeb = onOpenWeb
    )
}


@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    onAction: (SettingsActions) -> Unit,
    onOpenWeb: (String) -> Unit,
) {

    val themeMode = LocalThemeMode.current
    val language = LocalAppLanguage.current
    val packageManager = rememberPackageManager()
    val urlLauncher = rememberUrlLauncher()
    var showThemeSheet by remember { mutableStateOf(false) }
    LazyColumn {
        item {
            Column(
                modifier = modifier.fillMaxSize(),
            ) {
                AppListItem(
                    supportLabel = stringResource(themeMode.stringRes),
                    label = Res.string.appearance,
                    onClick = {
                        showThemeSheet = true
                    },
                    showArrow = true
                )

                AppListItem(
                    supportLabel = stringResource(language.stringRes),
                    label = Res.string.lang,
                    onClick = {
                        urlLauncher.openAppSettings()
                    },
                    showArrow = true
                )
                AppListItem(
                    label = Res.string.send_feedback,
                    onClick = {},
                )
                AppListItem(
                    label = Res.string.leave_review,
                    onClick = {},
                )
                AppListItem(
                    label = Res.string.policy,
                    onClick = {
                        onOpenWeb("https:google.com")
                    },
                )
                AppListItem(
                    label = Res.string.terms_use,
                    onClick = {
                        onOpenWeb("https:google.com")

                    },
                )


                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text(
                        stringResource(Res.string.app_version, packageManager.getAppVersion()),
                        style = MaterialTheme.typography.bodySmall.copy(color = Color.Gray),
                        textAlign = TextAlign.Center,
                    )

                }

            }
        }
    }
    if (showThemeSheet) {
        AppBottomSheet(
            onDismiss = {
                showThemeSheet = false
            }
        ) {
            AppRadio(
                items = AppThemeMode.entries,
                title = stringResource(Res.string.appearance),
                current = themeMode,
                onChanged = {
                    onAction(SettingsActions.ChangeTheme(it))
                    showThemeSheet = false
                },
                labelContent = {
                    Text(
                        stringResource(it!!.stringRes),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            )
        }
    }
}