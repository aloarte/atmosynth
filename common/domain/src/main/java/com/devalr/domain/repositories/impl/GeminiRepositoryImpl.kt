package com.devalr.domain.repositories.impl

import com.devalr.data.datasources.GeminiDatasource
import com.devalr.domain.repositories.GeminiRepository

class GeminiRepositoryImpl(
    private val datasource: GeminiDatasource,
) : GeminiRepository {
    override suspend fun generateDaySummary(dataForPrompt: String): String {
        //TODO: Save the report on a preference in order to recover it for every day (prediction doesn't change). This way gemini calls are just done once per day
        return datasource.generateDaySummary(dataForPrompt = dataForPrompt)
    }

}
