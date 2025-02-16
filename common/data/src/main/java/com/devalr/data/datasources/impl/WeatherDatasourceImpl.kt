package com.devalr.data.datasources.impl

import com.devalr.data.Secrets
import com.devalr.data.datasources.WeatherDatasource
import com.devalr.data.dto.DataResponse
import com.devalr.data.dto.dailyweather.daily.DailyWeatherDto
import com.devalr.data.dto.dailyweather.hourly.HourlyWeatherDto
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
    private val secrets: Secrets
) : WeatherDatasource {

    enum class WeatherQuery {
        ByHours, ByDays
    }

    companion object {
        const val AEMET_HOST = "opendata.aemet.es"
        const val FETCH_HOURLY_WEATHER_PATH =
            "opendata/api/prediccion/especifica/municipio/horaria/"
        const val FETCH_DAILY_WEATHER_PATH = "opendata/api/prediccion/especifica/municipio/diaria/"
    }

    override suspend fun fetchDailyWeather(cityCode: String): DailyWeatherDto? {
        val weatherDataUrl = fetchAemetData(cityCode = cityCode, WeatherQuery.ByDays)
        return fetchDailyWeatherData(weatherDataUrl)
    }

    override suspend fun fetchHourlyWeather(cityCode: String): HourlyWeatherDto? {
        val weatherDataUrl = fetchAemetData(cityCode = cityCode, WeatherQuery.ByHours)
        return fetchHourlyWeatherData(weatherDataUrl)
    }

    private suspend fun fetchAemetData(cityCode: String, query: WeatherQuery): String {
        val response =
            httpClient.get {
                url {
                    protocol = URLProtocol.HTTPS
                    host = AEMET_HOST
                    encodedPath = getUrl(cityCode, query)
                }
                header("api_key", secrets.getAemetApiKey())
            }

        return response.body<DataResponse>().requestDataUrl
    }

    private fun getUrl(cityCode: String, query: WeatherQuery) = when (query) {
        WeatherQuery.ByHours -> "$FETCH_HOURLY_WEATHER_PATH$cityCode"
        WeatherQuery.ByDays -> "$FETCH_DAILY_WEATHER_PATH$cityCode"
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

    private suspend fun fetchHourlyWeatherData(url: String): HourlyWeatherDto? {
        val response =
            httpClient.get(url) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
            }

        return Json
            .decodeFromString<List<HourlyWeatherDto>>(response.body() as String)
            .firstOrNull()
    }
}
