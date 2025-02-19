package com.devalr.data.databases

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [PromptResultEntity::class], version = 1)
abstract class PromptResultDatabase : RoomDatabase() {
    abstract fun promptResultDao(): PromptResultDao
}
