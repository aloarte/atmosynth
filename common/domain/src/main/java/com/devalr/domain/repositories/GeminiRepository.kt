package com.devalr.domain.repositories

interface GeminiRepository {

    suspend fun generateDaySummary(dataForPrompt: String): String
    suspend fun generateHourlySummary(dataForPrompt: String): String
    suspend fun generatePrecipitationsSummary(hourlyPrecipitations:String,todayPrecipitations:String): String
    suspend fun generateHumiditySummary(humidityData:String,temperatureData:String): String
    suspend fun generateWindSummary(windData: String): String
    suspend fun generateUvSummary(uv: String): String
}
