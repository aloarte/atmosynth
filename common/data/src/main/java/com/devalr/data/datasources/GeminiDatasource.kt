package com.devalr.data.datasources

interface GeminiDatasource {
    suspend fun generateDaySummary(dataForPrompt: String): String
    suspend fun generateHumiditySummary(dataForPrompt: String): String
    suspend fun generateWindSummary(dataForPrompt: String): String
}
