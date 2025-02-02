package com.devalr.data.datasources.impl

import android.util.Log
import com.devalr.data.Secrets
import com.devalr.data.datasources.WeatherDatasource
import com.devalr.data.dto.DataResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.parameter
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath

class WeatherDatasourceImpl(
    private val httpClient: HttpClient,
) : WeatherDatasource {
    companion object {
        const val AEMET_HOST = "opendata.aemet.es"
        const val FETCH_WEATHER_PATH = ""
    }

    override suspend fun fetchDailyWeather(cityCode: String): List<String> {
        val apiKey1 = Secrets.getApiKeyFromNative()
        val response =
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = AEMET_HOST
                    encodedPath = "opendata/api/prediccion/especifica/municipio/horaria/$cityCode"
                }
                parameter("param1", "value1")
                header(
                    "api_key",
                    apiKey1,
                )
            }

        val r = response.body<DataResponse>()
        Log.d("ALRALR", "Resultado: $r")
        return emptyList()
    }
}
