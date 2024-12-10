package com.mohaberabi.tatbeeq

import KoinInit
import androidx.compose.ui.window.ComposeUIViewController
import com.mohaberabi.tatbeeq.app.TatbeeqApp

fun MainViewController(

) = ComposeUIViewController(
    configure = {
        KoinInit().init()
    }
) {
    TatbeeqApp()
}
