package com.devalr.domain

import com.devalr.data.GeminiDatasource

class GeminiRepositoryImpl(private val datasource: GeminiDatasource) : GeminiRepository {
    override suspend fun generateDaySummary(dataForPrompt: String): List<String> {
        return datasource.generateDaySummary(dataForPrompt = dataForPrompt)
    }
}