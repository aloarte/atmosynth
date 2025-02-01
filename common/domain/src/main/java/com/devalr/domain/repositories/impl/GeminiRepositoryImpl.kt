package com.devalr.domain.repositories.impl

import com.devalr.data.datasources.GeminiDatasource
import com.devalr.domain.repositories.GeminiRepository

class GeminiRepositoryImpl(
    private val datasource: GeminiDatasource,
) : GeminiRepository {
    override suspend fun generateDaySummary(dataForPrompt: String): List<String> =
        datasource.generateDaySummary(dataForPrompt = dataForPrompt)
}
