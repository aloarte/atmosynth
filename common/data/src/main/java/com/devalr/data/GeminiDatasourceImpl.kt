package com.devalr.data

import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content

class GeminiDatasourceImpl(private val model: GenerativeModel) : GeminiDatasource {

    private val prompt =
        "Generame, separado por el caracter \n dos frases que contengan la palabra "

    override suspend fun generateDaySummary(dataForPrompt: String): List<String> {
        val response = model.generateContent(
            content {
                text(prompt + dataForPrompt)
            }
        )

        return response.text?.split("\n") ?: emptyList()
    }
}