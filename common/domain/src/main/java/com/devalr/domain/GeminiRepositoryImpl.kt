package com.devalr.domain

import com.devalr.data.GeminiDatasourceImpl

class GeminiRepositoryImpl : GeminiRepository {
    override suspend fun generateDaySummary(dataForPrompt: String): List<String> {
        val ds = GeminiDatasourceImpl()
        return ds.generateDaySummary(dataForPrompt = dataForPrompt)
    }
}