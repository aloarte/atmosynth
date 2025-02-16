package com.devalr.domain.repositories.impl

import com.devalr.data.datasources.WeatherDatasource
import com.devalr.data.dto.dailyweather.daily.DailyWeatherDto
import com.devalr.data.dto.dailyweather.hourly.HourlyWeatherDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.daily.DailyWeatherDataBo
import com.devalr.domain.model.weather.hourly.HourlyWeatherDataBo
import com.devalr.domain.repositories.WeatherRepository

class WeatherRepositoryImpl(
    private val datasource: WeatherDatasource,
    private val hourlyWeatherMapper: Mapper<HourlyWeatherDto?, HourlyWeatherDataBo?>,
    private val dailyWeatherMapper: Mapper<DailyWeatherDto?, DailyWeatherDataBo?>
) : WeatherRepository {
    override suspend fun fetchDailyWeather(cityCode: String): DailyWeatherDataBo? {
        val weatherDto = datasource.fetchDailyWeather((cityCode))
        return dailyWeatherMapper.transform(weatherDto)
    }

    override suspend fun fetchHourlyWeather(cityCode: String): HourlyWeatherDataBo? {
        val weatherDto = datasource.fetchHourlyWeather((cityCode))
        return hourlyWeatherMapper.transform(weatherDto)
    }
}
