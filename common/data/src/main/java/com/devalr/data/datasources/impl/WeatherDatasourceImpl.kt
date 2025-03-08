package com.devalr.data.datasources.impl

import com.devalr.data.datasources.WeatherDatasource
import com.devalr.data.datasources.helpers.AemetApiHelper
import com.devalr.data.datasources.helpers.Constants.FETCH_DAILY_WEATHER_PATH
import com.devalr.data.datasources.helpers.Constants.FETCH_HOURLY_WEATHER_PATH
import com.devalr.data.datasources.helpers.WeatherQuery
import com.devalr.data.dto.dailyweather.daily.DailyWeatherDto
import com.devalr.data.dto.dailyweather.hourly.HourlyWeatherDto
import io.ktor.client.HttpClient

class WeatherDatasourceImpl(
    private val httpClient: HttpClient,
    private val helper: AemetApiHelper
) : WeatherDatasource {

    override suspend fun fetchDailyWeather(cityCode: String): DailyWeatherDto? =
        fetchDailyWeatherData(
            weatherUrl = helper.fetchAemetData(
                httpClient = httpClient,
                dataUrl = getUrl(cityCode = cityCode, query = WeatherQuery.ByDays)
            )
        )

    override suspend fun fetchHourlyWeather(cityCode: String): HourlyWeatherDto? =
        fetchHourlyWeatherData(
            weatherUrl = helper.fetchAemetData(
                httpClient = httpClient,
                dataUrl = getUrl(cityCode = cityCode, query = WeatherQuery.ByHours)
            )
        )

    private fun getUrl(cityCode: String, query: WeatherQuery) = when (query) {
        WeatherQuery.ByHours -> "$FETCH_HOURLY_WEATHER_PATH$cityCode"
        WeatherQuery.ByDays -> "$FETCH_DAILY_WEATHER_PATH$cityCode"
    }

    private suspend fun fetchDailyWeatherData(weatherUrl: String): DailyWeatherDto? {
        val weatherData: List<DailyWeatherDto> = helper.runSafely(weatherUrl, httpClient)
        return weatherData.firstOrNull()
    }

    private suspend fun fetchHourlyWeatherData(weatherUrl: String): HourlyWeatherDto? {
        val weatherData: List<HourlyWeatherDto> = helper.runSafely(weatherUrl, httpClient)
        return weatherData.firstOrNull()
    }

}
