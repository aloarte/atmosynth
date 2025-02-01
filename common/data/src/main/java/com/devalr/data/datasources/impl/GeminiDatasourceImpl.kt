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
        "Generame, separado por el caracter \n dos frases que contengan la palabra "

    override suspend fun generateDaySummary(dataForPrompt: String): List<String> =
        runSafely {
            val response =
                model.generateContent(
                    content {
                        text(prompt + dataForPrompt)
                    },
                )

            response.text?.split("\n") ?: emptyList()
        }

    private suspend inline fun runSafely(crossinline execute: suspend () -> List<String>) =
        withContext(Dispatchers.IO) {
            try {
                execute()
            } catch (exception: ServerException) {
                Log.d("ALRALR", "exception ${exception.message}")
                // TODO: Return an error here
                emptyList()
            }
        }
}
