package com.devalr.domain.repositories

interface GeminiRepository {
    suspend fun generateDaySummary(dataForPrompt: String): String
    suspend fun generateHumiditySummary(humidityData:String): String
}
