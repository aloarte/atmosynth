package com.devalr.domain

interface GeminiRepository {

    suspend fun generateDaySummary(dataForPrompt: String): List<String>

}