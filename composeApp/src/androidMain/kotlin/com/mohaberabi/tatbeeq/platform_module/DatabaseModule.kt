package com.mohaberabi.tatbeeq.platform_module

import android.content.Context
import androidx.room.Room
import com.mohaberabi.tatbeeq.core.data.database.AppDatabase
import kotlinx.coroutines.Dispatchers
import okhttp3.Dispatcher
import org.koin.dsl.module


internal fun createDatabase(
    context: Context,
): AppDatabase {
    val dbFile = context.getDatabasePath("tatbeeq.db")
    return Room.databaseBuilder<AppDatabase>(
        context,
        name = dbFile.absolutePath,
    ).setQueryCoroutineContext(Dispatchers.IO)
        .build()
}