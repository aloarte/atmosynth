package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.DailyWeatherDto
import com.devalr.data.dto.dailyweather.DayDto
import com.devalr.domain.model.weather.DailyPredictionBo
import com.devalr.domain.model.weather.DailyWeatherBo

class DailyWeatherMapper(
    private val dayMapper: Mapper<DayDto, DailyPredictionBo>,
) : Mapper<DailyWeatherDto?, DailyWeatherBo?>() {
    override fun transform(data: DailyWeatherDto?): DailyWeatherBo? =
        data?.let {
            DailyWeatherBo(
                predictions = data.prediction.day.map { dayMapper.transform(it) },
            )
        }
}
