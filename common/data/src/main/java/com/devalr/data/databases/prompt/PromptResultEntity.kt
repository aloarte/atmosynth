package com.devalr.data.databases.prompt

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "promptResults")
data class PromptResultEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val date: String,
    val promptResult: String
)