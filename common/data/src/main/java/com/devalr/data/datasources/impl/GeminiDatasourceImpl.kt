package com.devalr.data.datasources.impl

import android.util.Log
import com.devalr.data.datasources.GeminiDatasource
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.ServerException
import com.google.ai.client.generativeai.type.content
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GeminiDatasourceImpl(
    private val model: GenerativeModel,
) : GeminiDatasource {
    private val prompt =
        "Con los siguientes datos que te voy a proporcionar sobre el tiempo atmosférico, generame un resumen del pronóstico en no más de 7-8 líneas de texto. Debe incluir recomendaciones y explicaciones sobre: UV, humedad y viento. No incluyas la fecha en el resumen. Los datos son estos:"

    override suspend fun generateDaySummary(dataForPrompt: String): String =
        runSafely {
            val response =
                model.generateContent(
                    content {
                        text(prompt + dataForPrompt)
                    },
                )

            response.text ?: ""
        }

    private suspend inline fun runSafely(crossinline execute: suspend () -> String) =
        withContext(Dispatchers.IO) {
            try {
                execute()
            } catch (exception: ServerException) {
                Log.d("ALRALR", "exception ${exception.message}")
                // TODO: Return an error here
                ""
            }
        }
}
