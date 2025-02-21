package com.devalr.domain.repositories.impl

import com.devalr.data.databases.PromptResultDao
import com.devalr.data.databases.PromptResultEntity
import com.devalr.data.datasources.GeminiDatasource
import com.devalr.domain.repositories.GeminiRepository
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class GeminiRepositoryImpl(
    private val datasource: GeminiDatasource,
    private val database: PromptResultDao
) : GeminiRepository {
    override suspend fun generateDaySummary(dataForPrompt: String): String {
        val databasePrompt = database.getDailyPrompt(getDate())
        return if (databasePrompt != null) {
            databasePrompt.promptResult
        } else {
            val promptResult = datasource.generateDaySummary(dataForPrompt = dataForPrompt)
            if (promptResult.isBlank().not()) {
                database.removeAllDailyPrompts()
                database.insertDailyPromptResult(
                    PromptResultEntity(
                        date = getDate(),
                        promptResult = promptResult
                    )
                )
            }

            promptResult
        }
    }

    private fun getDate(): String {
        val date = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
    }
}

