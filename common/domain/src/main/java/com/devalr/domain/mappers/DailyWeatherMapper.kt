package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.hourly.HourlyWeatherDto
import com.devalr.data.dto.dailyweather.hourly.HourlyDto
import com.devalr.domain.model.weather.HourlyPredictionBo
import com.devalr.domain.model.weather.HourlyWeatherDataBo

class DailyWeatherMapper(
    private val dayMapper: Mapper<HourlyDto, HourlyPredictionBo>,
) : Mapper<HourlyWeatherDto?, HourlyWeatherDataBo?>() {
    override fun transform(data: HourlyWeatherDto?): HourlyWeatherDataBo? =
        data?.let {
            HourlyWeatherDataBo(predictions = data.prediction.day.map { dayMapper.transform(it) })
        }
}
