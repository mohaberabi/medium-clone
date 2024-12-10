package com.mohaberabi.tatbeeq.core.data.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.RoomDatabaseConstructor
import com.mohaberabi.tatbeeq.core.data.database.dao.ArticleDao
import com.mohaberabi.tatbeeq.core.data.database.dao.ArticleDetailDao
import com.mohaberabi.tatbeeq.core.data.database.entity.ArticleDetailEntity
import com.mohaberabi.tatbeeq.core.data.database.entity.ArticleEntity

@Database(
    entities = [
        ArticleDetailEntity::class,
        ArticleEntity::class
    ],
    version = 1,
)

@ConstructedBy(AppDatabaseCreator::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun articleDetailDao(): ArticleDetailDao
}

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")

expect object AppDatabaseCreator : RoomDatabaseConstructor<AppDatabase>