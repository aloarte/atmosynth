package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.DayDto
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.model.weather.DailyPredictionBo
import com.devalr.domain.model.weather.TemperatureRelationBo

class DayMapper(
    private val temperatureMapper: Mapper<ValueInTimeDto, TemperatureRelationBo>,
) : Mapper<DayDto, DailyPredictionBo>() {
    override fun transform(data: DayDto): DailyPredictionBo =
        DailyPredictionBo(
            date = data.date,
            temperatures = data.temperature.map { temperatureMapper.transform(it) },
        )
}
