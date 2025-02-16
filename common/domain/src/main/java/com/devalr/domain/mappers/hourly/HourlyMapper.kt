package com.devalr.domain.mappers.hourly

import com.devalr.data.dto.dailyweather.hourly.HourlyDto
import com.devalr.data.dto.dailyweather.hourly.HourlySkyValueInTimeDto
import com.devalr.data.dto.dailyweather.hourly.HourlyValueInTimeDto
import com.devalr.domain.HourlyEventData
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.mappers.params.DateMapperParams
import com.devalr.domain.mappers.params.ValueInTimeParams
import com.devalr.domain.mergers.DayMerger
import com.devalr.domain.model.enums.SunEvent
import com.devalr.domain.model.weather.hourly.HourlyPredictionBo
import com.devalr.domain.model.weather.common.HumidityRelationBo
import com.devalr.domain.model.weather.common.RainRelationBo
import com.devalr.domain.model.weather.common.SkyRelationBo
import com.devalr.domain.model.weather.common.SnowRelationBo
import com.devalr.domain.model.weather.common.TemperatureRelationBo
import com.devalr.domain.model.weather.common.ThermalRelationBo
import java.time.LocalDateTime

class HourlyMapper(
    private val dateMapper: Mapper<DateMapperParams, LocalDateTime>,
    private val humidityMapper: Mapper<HourlyValueInTimeDto, HumidityRelationBo>,
    private val rainMapper: Mapper<HourlyValueInTimeDto, RainRelationBo>,
    private val skyMapper: Mapper<HourlySkyValueInTimeDto, SkyRelationBo>,
    private val snowMapper: Mapper<HourlyValueInTimeDto, SnowRelationBo>,
    private val temperatureMapper: Mapper<ValueInTimeParams, TemperatureRelationBo>,
    private val thermalMapper: Mapper<HourlyValueInTimeDto, ThermalRelationBo>,
    private val dayMerger: DayMerger
) : Mapper<HourlyDto, HourlyPredictionBo>() {
    override fun transform(data: HourlyDto): HourlyPredictionBo =
        HourlyPredictionBo(
            date = data.date,
            hourlyData = dayMerger.merge(
                humidityList = data.relativeHumidity.map { humidityMapper.transform(it) },
                rainList = data.rain.map { rainMapper.transform(it) },
                skyList = data.skyState.map { skyMapper.transform(it) },
                snow = data.snow.map { snowMapper.transform(it) },
                temperatureList = data.temperature.map {
                    temperatureMapper.transform(ValueInTimeParams(it, data.date))
                },
                thermalList = data.thermalSensation.map { thermalMapper.transform(it) }
            ),
            sunEvents = listOf(
                HourlyEventData(
                    event = SunEvent.Sunrise,
                    time = dateMapper.transform(DateMapperParams(data.date, data.sunriseTime))
                ),
                HourlyEventData(
                    event = SunEvent.Sunset,
                    time = dateMapper.transform(DateMapperParams(data.date, data.sunsetTime))
                )
            )
        )
}
