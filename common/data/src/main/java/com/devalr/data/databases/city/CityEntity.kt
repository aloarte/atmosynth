package com.devalr.data.databases.city

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val cityId: String,
    val population: String,
    val name: String,
    val selected: Boolean = false,
    val active: Boolean = false
)