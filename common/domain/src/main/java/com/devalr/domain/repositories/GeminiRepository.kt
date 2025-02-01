package com.devalr.domain.repositories

interface GeminiRepository {
    suspend fun generateDaySummary(dataForPrompt: String): List<String>
}
