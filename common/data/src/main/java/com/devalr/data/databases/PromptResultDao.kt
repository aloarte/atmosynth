package com.devalr.data.databases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PromptResultDao {
    @Insert
    suspend fun insertDailyPromptResult(promptResult: PromptResultEntity)

    @Query("SELECT * FROM promptResults WHERE date = :date")
    suspend fun getDailyPrompt(date: String): PromptResultEntity?

    @Query("DELETE FROM promptResults")
    suspend fun removeAllDailyPrompts()
}