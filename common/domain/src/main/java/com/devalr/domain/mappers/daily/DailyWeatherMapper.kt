package com.devalr.domain.mappers.daily

import com.devalr.data.dto.dailyweather.daily.DailyDto
import com.devalr.data.dto.dailyweather.daily.DailyWeatherDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.daily.DailyWeatherBo
import com.devalr.domain.model.weather.daily.DailyWeatherDataBo

class DailyWeatherMapper(
    private val dayMapper: Mapper<DailyDto, DailyWeatherBo>,
) : Mapper<DailyWeatherDto?, DailyWeatherDataBo?>() {
    override fun transform(data: DailyWeatherDto?): DailyWeatherDataBo? =
        data?.let {
            DailyWeatherDataBo(predictions = data.prediction.day.map { dayMapper.transform(it) })
        }
}
