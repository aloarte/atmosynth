package com.devalr.data.datasources.impl

import com.devalr.data.Secrets
import com.devalr.data.datasources.WeatherDatasource
import com.devalr.data.dto.DataResponse
import com.devalr.data.dto.dailyweather.DailyWeatherDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.encodedPath
import kotlinx.serialization.json.Json

class WeatherDatasourceImpl(
    private val httpClient: HttpClient,
    private val secrets: Secrets,
) : WeatherDatasource {
    companion object {
        const val AEMET_HOST = "opendata.aemet.es"
        const val FETCH_DAILY_WEATHER_PATH = "opendata/api/prediccion/especifica/municipio/horaria/"
    }

    override suspend fun fetchDailyWeather(cityCode: String): DailyWeatherDto? {
        val weatherDataUrl = fetchWeatherDataUrl(cityCode)
        return fetchDailyWeatherData(weatherDataUrl)
    }

    private suspend fun fetchWeatherDataUrl(cityCode: String): String {
        val response =
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = AEMET_HOST
                    encodedPath = "$FETCH_DAILY_WEATHER_PATH$cityCode"
                }
                header("api_key", secrets.getAemetApiKey())
            }

        return response.body<DataResponse>().requestDataUrl
    }

    private suspend fun fetchDailyWeatherData(url: String): DailyWeatherDto? {
        val response =
            httpClient.get(url) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

        return Json
            .decodeFromString<List<DailyWeatherDto>>(response.body() as String)
            .firstOrNull()
    }
}
