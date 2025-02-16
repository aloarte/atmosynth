package com.devalr.domain.mergers

import com.devalr.domain.model.weather.hourly.HourlyWeatherBo
import com.devalr.domain.model.weather.common.HumidityRelationBo
import com.devalr.domain.model.weather.common.RainRelationBo
import com.devalr.domain.model.weather.common.SkyRelationBo
import com.devalr.domain.model.weather.common.SnowRelationBo
import com.devalr.domain.model.weather.common.TemperatureRelationBo
import com.devalr.domain.model.weather.common.ThermalRelationBo

class DayMerger {
    fun merge(
        humidityList: List<HumidityRelationBo>,
        rainList: List<RainRelationBo>,
        skyList: List<SkyRelationBo>,
        snow: List<SnowRelationBo>,
        temperatureList: List<TemperatureRelationBo>,
        thermalList: List<ThermalRelationBo>
    ): List<HourlyWeatherBo> =
        temperatureList.mapNotNull { temperatureRelation ->
            val humidity = humidityList.find { it.time == temperatureRelation.time }
            val rainProbability = rainList.find { it.time == temperatureRelation.time }
            val skyState = skyList.find { it.time == temperatureRelation.time }
            val snowProbability = snow.find { it.time == temperatureRelation.time }
            val thermalSensation = thermalList.find { it.time == temperatureRelation.time }
            if (humidity != null &&
                rainProbability != null &&
                skyState != null &&
                snowProbability != null &&
                thermalSensation != null
            ) {
                HourlyWeatherBo(
                    humidity = humidity.humidity,
                    rainProbability = rainProbability.rainProbability,
                    skyState = skyState.skyState,
                    snowProbability = snowProbability.snowProbability,
                    temperature = temperatureRelation.temperature,
                    thermalSensation = thermalSensation.thermalSensation,
                    time = temperatureRelation.time,
                    completeTime = temperatureRelation.completeTime
                )
            } else {
                null
            }
        }
}
