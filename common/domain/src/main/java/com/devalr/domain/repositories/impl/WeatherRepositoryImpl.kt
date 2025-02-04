package com.devalr.domain.repositories.impl

import com.devalr.data.datasources.WeatherDatasource
import com.devalr.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val datasource: WeatherDatasource,
) : WeatherRepository {
    override suspend fun fetchDailyWeather(cityCode: String): List<String> {
        datasource.fetchDailyWeather((cityCode))
        return emptyList()
    }
}
