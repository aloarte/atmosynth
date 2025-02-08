package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.DayDto
import com.devalr.data.dto.dailyweather.SkyValueInTimeDto
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.SunEventData
import com.devalr.domain.mergers.DayMerger
import com.devalr.domain.model.weather.DailyPredictionBo
import com.devalr.domain.model.weather.HumidityRelationBo
import com.devalr.domain.model.weather.RainRelationBo
import com.devalr.domain.model.weather.SkyRelationBo
import com.devalr.domain.model.weather.SnowRelationBo
import com.devalr.domain.model.weather.TemperatureRelationBo
import com.devalr.domain.model.weather.ThermalRelationBo

class DayMapper(
    private val humidityMapper: Mapper<ValueInTimeDto, HumidityRelationBo>,
    private val rainMapper: Mapper<ValueInTimeDto, RainRelationBo>,
    private val skyMapper: Mapper<SkyValueInTimeDto, SkyRelationBo>,
    private val snowMapper: Mapper<ValueInTimeDto, SnowRelationBo>,
    private val sunEventsMapper: Mapper<Map<String, String>, List<SunEventData>>,
    private val temperatureMapper: Mapper<ValueInTimeDto, TemperatureRelationBo>,
    private val thermalMapper: Mapper<ValueInTimeDto, ThermalRelationBo>,
    private val dayMerger: DayMerger
) : Mapper<DayDto, DailyPredictionBo>() {
    override fun transform(data: DayDto): DailyPredictionBo =
        DailyPredictionBo(
            date = data.date,
            hourlyData =
                dayMerger.merge(
                    humidityList = data.relativeHumidity.map { humidityMapper.transform(it) },
                    rainList = data.rain.map { rainMapper.transform(it) },
                    skyList = data.skyState.map { skyMapper.transform(it) },
                    snow = data.snow.map { snowMapper.transform(it) },
                    temperatureList = data.temperature.map { temperatureMapper.transform(it) },
                    thermalList = data.thermalSensation.map { thermalMapper.transform(it) }
                ),
            sunEvents =
                sunEventsMapper.transform(
                    mapOf(
                        "SUNRISE" to data.sunriseTime,
                        "SUNSET" to data.sunsetTime
                    )
                )
        )
}
