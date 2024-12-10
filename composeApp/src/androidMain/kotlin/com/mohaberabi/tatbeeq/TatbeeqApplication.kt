package com.mohaberabi.tatbeeq

import KoinInit
import android.app.Application


class TatbeeqApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInit(this).init()
    }
}