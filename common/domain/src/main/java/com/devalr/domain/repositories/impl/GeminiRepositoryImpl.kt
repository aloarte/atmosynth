package com.devalr.domain.repositories.impl

import com.devalr.data.databases.prompt.PromptResultDao
import com.devalr.data.databases.prompt.PromptResultEntity
import com.devalr.data.datasources.GeminiDatasource
import com.devalr.domain.repositories.GeminiRepository
import java.time.LocalDateTime
import java.time.ZoneId
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

    override suspend fun generateHourlySummary(dataForPrompt: String): String {
        val databasePrompt = database.getDailyPrompt(getDate() + " Hourly")
        return if (databasePrompt != null) {
            databasePrompt.promptResult
        } else {
            val promptResult = datasource.generateHourlySummary(dataForPrompt = dataForPrompt)
            if (promptResult.isBlank().not()) {
                database.removeAllHourlyPrompts()
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

    override suspend fun generatePrecipitationsSummary(
        hourlyPrecipitations: String,
        todayPrecipitations: String
    ): String {
        val precipitationsPromptKey = getDate() + " Precipitations"
        val databasePrompt = database.getDailyPrompt(precipitationsPromptKey)
        return if (databasePrompt != null) {
            databasePrompt.promptResult
        } else {
            val promptResult =
                datasource.generatePrecipitationsSummary(
                    dataHourlyPrecipitations = hourlyPrecipitations,
                    dataTodayPrecipitations = todayPrecipitations
                )
            if (promptResult.isBlank().not()) {
                database.removePrecipitationsDailyPrompts()
                database.insertDailyPromptResult(
                    PromptResultEntity(
                        date = precipitationsPromptKey,
                        promptResult = promptResult
                    )
                )
            }
            promptResult
        }
    }

    override suspend fun generateHumiditySummary(
        humidityData: String,
        temperatureData: String
    ): String {
        val humidityPromptKey = getDate() + " Humidity"
        val databasePrompt = database.getDailyPrompt(humidityPromptKey)
        return if (databasePrompt != null) {
            databasePrompt.promptResult
        } else {
            val promptResult = datasource.generateHumiditySummary(
                dataHumidityForPrompt = humidityData,
                dataTemperatureForPrompt = temperatureData
            )

            if (promptResult.isBlank().not()) {
                database.removeHumidityDailyPrompts()
                database.insertDailyPromptResult(
                    PromptResultEntity(
                        date = humidityPromptKey,
                        promptResult = promptResult
                    )
                )
            }
            promptResult
        }
    }

    override suspend fun generateWindSummary(windData: String): String {
        val windPromptKey = getDate() + " Wind"
        val databasePrompt = database.getDailyPrompt(windPromptKey)
        return if (databasePrompt != null) {
            databasePrompt.promptResult
        } else {
            val promptResult = datasource.generateWindSummary(dataForPrompt = windData)
            if (promptResult.isBlank().not()) {
                database.removeWindDailyPrompts()
                database.insertDailyPromptResult(
                    PromptResultEntity(
                        date = windPromptKey,
                        promptResult = promptResult
                    )
                )
            }
            promptResult
        }
    }

    override suspend fun generateUvSummary(uv: String): String {
        val uvPromptKey = getDate() + " Uv"
        val databasePrompt = database.getDailyPrompt(uvPromptKey)
        return if (databasePrompt != null) {
            databasePrompt.promptResult
        } else {
            val promptResult = datasource.generateUvSummary(dataForPrompt = uv)
            if (promptResult.isBlank().not()) {
                database.removeUvDailyPrompts()
                database.insertDailyPromptResult(
                    PromptResultEntity(
                        date = uvPromptKey,
                        promptResult = promptResult
                    )
                )
            }
            promptResult
        }
    }

    override suspend fun resetSavedSummaries() {
        database.removeAllDailyPrompts()
    }

    private fun getDate(): String {
        val date = LocalDateTime.now(ZoneId.of("Europe/Madrid"))
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        return date.format(formatter)
    }
}

