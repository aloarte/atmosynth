package com.devalr.dayweather.mappers

import android.annotation.SuppressLint
import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.dayweather.model.enums.SkyStateIcon
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.weather.hourly.HourlyWeatherBo

class HourlyWeatherMapper(private val skyMapper: Mapper<SkyState, SkyStateIcon>) :
    Mapper<HourlyWeatherBo, HourlyWeatherVo>() {
    @SuppressLint("DefaultLocale")
    override fun transform(data: HourlyWeatherBo): HourlyWeatherVo =
        HourlyWeatherVo(
            hour = data.time.text,
            completeTime = data.completeTime,
            humidity = data.humidity.toString(),
            precipitationProbability = maxOf(
                data.rainProbability,
                data.snowProbability
            ).takeIf { it > 0.05F }
                ?.let { String.format("%.0f%%", it * 100) }
                .orEmpty(),
            temperature = "${data.temperature}º",
            thermalSensation = data.thermalSensation.toString(),
            skyState = skyMapper.transform(data.skyState)
        )
}
