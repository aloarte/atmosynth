package com.devalr.data.datasources

interface GeminiDatasource {
    suspend fun generateDaySummary(dataForPrompt: String): String
}
