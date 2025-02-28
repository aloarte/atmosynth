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

    @Query("DELETE FROM promptResults WHERE date LIKE '%Precipitations:%'")
    suspend fun removePrecipitationsDailyPrompts()

    @Query("DELETE FROM promptResults WHERE date LIKE '%WeatherMaxMin%'")
    suspend fun removeHumidityDailyPrompts()

    @Query("DELETE FROM promptResults WHERE date LIKE '%WindState%'")
    suspend fun removeWindDailyPrompts()

    @Query("DELETE FROM promptResults WHERE date LIKE '%Uv: %'")
    suspend fun removeUvDailyPrompts()

}