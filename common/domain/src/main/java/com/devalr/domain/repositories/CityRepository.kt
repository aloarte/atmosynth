package com.devalr.domain.repositories

import com.devalr.domain.model.CityBo
import com.devalr.domain.model.weather.daily.DailyWeatherDataBo
import com.devalr.domain.model.weather.hourly.HourlyWeatherDataBo

interface CityRepository {
    suspend fun fetchCities(): List<CityBo>
    suspend fun selectCity(city: CityBo)
    suspend fun getSelectedCities(): List<CityBo>
}
