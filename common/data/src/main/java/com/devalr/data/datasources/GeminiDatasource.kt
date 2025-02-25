package com.devalr.data.datasources

interface GeminiDatasource {
    suspend fun generateDaySummary(dataForPrompt: String): String
    suspend fun generateHumiditySummary(
        dataHumidityForPrompt: String,
        dataTemperatureForPrompt: String
    ): String

    suspend fun generateWindSummary(dataForPrompt: String): String
    suspend fun generateUvSummary(dataForPrompt: String): String
}
