package com.devalr.domain.mappers.hourly

import com.devalr.data.dto.dailyweather.hourly.HourlyWeatherDto
import com.devalr.data.dto.dailyweather.hourly.HourlyDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.hourly.HourlyPredictionBo
import com.devalr.domain.model.weather.hourly.HourlyWeatherDataBo

class HourlyWeatherMapper(
    private val hourlyMapper: Mapper<HourlyDto, HourlyPredictionBo>,
) : Mapper<HourlyWeatherDto?, HourlyWeatherDataBo?>() {
    override fun transform(data: HourlyWeatherDto?): HourlyWeatherDataBo? =
        data?.let {
            HourlyWeatherDataBo(predictions = data.prediction.day.map { hourlyMapper.transform(it) })
        }
}
