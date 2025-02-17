package com.devalr.dayweather.mappers

import com.devalr.dayweather.extensions.toCelsius
import com.devalr.dayweather.extensions.toHumidityPercentage
import com.devalr.dayweather.model.enums.SkyStateIcon
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.weather.daily.DailyWeatherBo
import java.time.LocalTime

class NowWeatherMapper(
    private val skyMapper: Mapper<SkyState, SkyStateIcon>
) : Mapper<DailyWeatherBo, NowWeatherDataVo>() {
    override fun transform(data: DailyWeatherBo): NowWeatherDataVo =
        NowWeatherDataVo(
            temperature = WeatherMaxMin(
                current = data.temperatures.valuesPerDayTime
                    .find { it.time == getNowDayTime() }
                    ?.value?.toCelsius()
                    ?: "0º",
                max = data.temperatures.max.toCelsius(),
                min = data.temperatures.min.toCelsius()
            ),
            thermalSensation = WeatherMaxMin(
                current = data.thermalSensation.valuesPerDayTime
                    .find { it.time == getNowDayTime() }
                    ?.value?.toCelsius()
                    ?: "0º",
                max = data.thermalSensation.max.toCelsius(),
                min = data.thermalSensation.min.toCelsius()
            ),
            humidity = WeatherMaxMin(
                current = "0%",
                max = data.humidity.max.toHumidityPercentage(),
                min = data.humidity.min.toHumidityPercentage()
            ),
            skyState = data.skyStates
                .find { it.time == getNowDayTime() }
                ?.state
                ?.let {
                    skyMapper.transform(
                        it
                    )
                } ?: SkyStateIcon.DayClear
        )


    private fun getNowDayTime(): DayTime {
        val currentTime = LocalTime.now()
        val hour = currentTime.hour

        return when (hour) {
            in 0..5 -> DayTime.EarlyMorning
            in 6..11 -> DayTime.Morning
            in 12..17 -> DayTime.Afternoon
            in 18..23 -> DayTime.Night
            else -> DayTime.Unknown
        }
    }
}
