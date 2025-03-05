package com.devalr.data.databases.city

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CityEntity::class], version = 2)
abstract class CityDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
}
