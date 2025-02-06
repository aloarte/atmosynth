package com.devalr.domain.repositories.impl

import com.devalr.data.datasources.WeatherDatasource
import com.devalr.data.dto.dailyweather.DailyWeatherDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.DailyWeatherBo
import com.devalr.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val datasource: WeatherDatasource,
    private val dailyWeatherMapper: Mapper<DailyWeatherDto?, DailyWeatherBo?>,
) : WeatherRepository {
    override suspend fun fetchDailyWeather(cityCode: String): DailyWeatherBo? {
        val weatherDto = datasource.fetchDailyWeather((cityCode))
        return dailyWeatherMapper.transform(weatherDto)
    }
}
