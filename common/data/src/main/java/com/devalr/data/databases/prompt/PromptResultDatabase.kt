package com.devalr.data.databases.prompt

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PromptResultEntity::class], version = 1)
abstract class PromptResultDatabase : RoomDatabase() {
    abstract fun promptResultDao(): PromptResultDao
}
