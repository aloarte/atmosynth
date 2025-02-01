package com.devalr.data

interface GeminiDatasource {
    suspend fun generateDaySummary(dataForPrompt: String): List<String>
}
