package com.devalr.domain.mergers

import com.devalr.domain.model.weather.HourlyWeatherBo
import com.devalr.domain.model.weather.HumidityRelationBo
import com.devalr.domain.model.weather.RainRelationBo
import com.devalr.domain.model.weather.SkyRelationBo
import com.devalr.domain.model.weather.SnowRelationBo
import com.devalr.domain.model.weather.TemperatureRelationBo
import com.devalr.domain.model.weather.ThermalRelationBo

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
