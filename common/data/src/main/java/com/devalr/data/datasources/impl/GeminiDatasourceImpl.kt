package com.devalr.data.datasources.impl

import com.devalr.data.datasources.GeminiDatasource
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ServerException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiDatasourceImpl(
    private val model: GenerativeModel,
) : GeminiDatasource {

    companion object {
        private const val WEATHER_SUMMARY_PROMPT =
            "Con los siguientes datos que te voy a proporcionar sobre el tiempo atmosférico, generame un resumen del pronóstico en no más de 6 líneas de texto. Debe incluir recomendaciones y explicaciones sobre: UV, humedad y viento. No incluyas la fecha en el resumen. Si hay información que consideres poco relevante puedes omitirla. No incluyas ningúna palabra que describa el resultado de lo que se te pide. Los datos son estos:"
        private const val HUMIDITY_SUMMARY_PROMPT =
            "Con los siguientes datos que te voy a proporcionar sobre la humedad y la temperatura del día de hoy, generame un texto de no mas de 5 líneas que me explique qué implicaciones tienen esos valores y que me aconseje. Centrate en la humedad, describirla y sus implicaciones, la temperatura utilizala a modo de contexto, no la menciones. Los datos son estos: "
        private const val WIND_SUMMARY_PROMPT =
            "Con los siguientes datos que te voy a proporcionar sobre el viento del día de hoy, generame un texto de no mas de 5 líneas que me explique qué implicaciones tienen esos valores y que me aconseje. Las unidades son km/h. Los datos son estos: "
        private const val UV_SUMMARY_PROMPT =
            "Te voy a pasar el dato del índice ultravioleta (UV) para el día de hoy. Generame un texto de no mas de 5 líneas que me explique qué es este índice, qué implicaciones tiene ese valor y que me aconseje al respecto. El índice UV hoy es de: "

    }

    override suspend fun generateDaySummary(dataForPrompt: String): String =
        runSafely {
            model.generateContent(
                content {
                    text(WEATHER_SUMMARY_PROMPT + dataForPrompt)
                },
            ).text ?: ""
        }

    override suspend fun generateHumiditySummary(
        dataHumidityForPrompt: String,
        dataTemperatureForPrompt: String
    ): String =
        runSafely {
            model.generateContent(
                content {
                    text(HUMIDITY_SUMMARY_PROMPT + "Humedad: " + dataHumidityForPrompt + " Temperatura: " + dataTemperatureForPrompt)
                },
            ).text ?: ""
        }

    override suspend fun generateWindSummary(dataForPrompt: String): String =
        runSafely {
            model.generateContent(
                content {
                    text(WIND_SUMMARY_PROMPT + dataForPrompt)
                },
            ).text ?: ""
        }

    override suspend fun generateUvSummary(dataForPrompt: String): String = runSafely {
        model.generateContent(
            content {
                text(UV_SUMMARY_PROMPT + dataForPrompt)
            },
        ).text ?: ""
    }

    private suspend inline fun runSafely(crossinline execute: suspend () -> String) =
        withContext(Dispatchers.IO) {
            try {
                execute()
            } catch (exception: ServerException) {
                // TODO: Return an error here
                ""
            }
        }
}
