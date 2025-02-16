package com.devalr.domain.mappers.daily

import com.devalr.data.dto.dailyweather.daily.DailyDto
import com.devalr.data.dto.dailyweather.daily.DailyMaxMinValuesDto
import com.devalr.data.dto.dailyweather.daily.DailySkyValueInTimeDto
import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeIntDto
import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeStrDto
import com.devalr.data.dto.dailyweather.daily.DailyWindInTimeDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.weather.daily.DailySkyState
import com.devalr.domain.model.weather.daily.DailyWeatherBo
import com.devalr.domain.model.weather.daily.DailyWindState
import com.devalr.domain.model.weather.daily.MaxMinValueBo
import com.devalr.domain.model.weather.daily.PrecipitationProbability
import java.time.LocalDateTime

class DailyMapper(
    private val precipitationRainMapper: Mapper<DailyValueInTimeIntDto, PrecipitationProbability>,
    private val precipitationSnowMapper: Mapper<DailyValueInTimeStrDto, PrecipitationProbability>,
    private val windStateMapper: Mapper<DailyWindInTimeDto, DailyWindState>,
    private val skyStateMapper: Mapper<DailySkyValueInTimeDto, DailySkyState>,
    private val minMaxValueMapper: Mapper<DailyMaxMinValuesDto, MaxMinValueBo>
) : Mapper<DailyDto, DailyWeatherBo>() {

    override fun transform(data: DailyDto): DailyWeatherBo =
        DailyWeatherBo(
            skyStates = data.skyState.map { skyStateMapper.transform(it) }
                .filter { it.time != DayTime.Unknown },
            rainProbabilities = data.rainProbability.map { precipitationRainMapper.transform(it) }
                .filter { it.time != DayTime.Unknown },
            snowProbabilities = data.snowProbability.map { precipitationSnowMapper.transform(it) }
                .filter { it.time != DayTime.Unknown },
            windState = data.wind.map { windStateMapper.transform(it) }
                .filter { it.time != DayTime.Unknown },
            humidity = minMaxValueMapper.transform(data.relativeHumidity),
            temperatures = minMaxValueMapper.transform(data.temperature),
            thermalSensation = minMaxValueMapper.transform(data.thermalSensation),
            date = LocalDateTime.parse(data.date),
            uvMax = data.uvMax ?: 0
        )
}
