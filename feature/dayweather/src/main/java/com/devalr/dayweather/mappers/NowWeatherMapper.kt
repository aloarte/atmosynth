package com.devalr.dayweather.mappers

import com.devalr.dayweather.extensions.toCelsius
import com.devalr.dayweather.extensions.toHumidityPercentage
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.dayweather.model.now.WindState
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.weather.daily.DailyWeatherBo
import com.devalr.domain.model.weather.daily.DailyWindState
import com.devalr.framework.enums.AnimationsType
import java.time.LocalTime

class NowWeatherMapper(
    private val animationSkyMapper: Mapper<AnimationSkyEnumMapper.Params, AnimationsType>,
    private val windDirectionMapper: Mapper<DailyWindState?, WindState>,
) : Mapper<DailyWeatherBo, NowWeatherDataVo>() {
    override fun transform(data: DailyWeatherBo): NowWeatherDataVo {
        val currentTemperature = data.temperatures.valuesPerDayTime
            .find { it.time == getNowDayTime() }
            ?.value
        return NowWeatherDataVo(
            temperature = WeatherMaxMin(
                current = currentTemperature?.toCelsius() ?: "0º",
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
                current = data.humidity.valuesPerDayTime
                    .find { it.time == getNowDayTime() }?.value.toString(),
                max = data.humidity.max.toHumidityPercentage(),
                min = data.humidity.min.toHumidityPercentage()
            ),
            wind = windDirectionMapper.transform(data.windState
                .find { it.time == getNowDayTime() }
            ),
            skyAnimation = animationSkyMapper.transform(
                AnimationSkyEnumMapper.Params(
                    skyState = data.skyStates
                        .find { it.time == getNowDayTime() }
                        ?.state ?: SkyState.Unknown,
                    temperature = currentTemperature ?: throw IllegalStateException("Temperature not found"),
                    windValue = 3) //TODO Check this wind value
            ),
            uvValue = data.uvMax.toString(),
            snowProbability = data.snowProbabilities
                .find { it.time == getNowDayTime() }
                ?.probability.toString(),
            rainProbability = data.rainProbabilities
                .find { it.time == getNowDayTime() }
                ?.probability.toString()
        )
    }


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
