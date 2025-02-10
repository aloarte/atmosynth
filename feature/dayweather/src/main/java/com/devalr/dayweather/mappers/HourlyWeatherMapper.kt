package com.devalr.dayweather.mappers

import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.HourlyWeatherBo

class HourlyWeatherMapper : Mapper<HourlyWeatherBo, HourlyWeatherVo>() {
    override fun transform(data: HourlyWeatherBo): HourlyWeatherVo =
        HourlyWeatherVo(
            hour = data.time.text,
            completeTime = data.completeTime,
            humidity = data.humidity.toString(),
            rainProbability = data.rainProbability.toString(),
            snowProbability = data.snowProbability.toString(),
            temperature = data.temperature.toString(),
            thermalSensation = data.thermalSensation.toString()
        )
}
