package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.DayDto
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.mergers.DayMerger
import com.devalr.domain.model.weather.DailyPredictionBo
import com.devalr.domain.model.weather.RainRelationBo
import com.devalr.domain.model.weather.TemperatureRelationBo

class DayMapper(
    private val temperatureMapper: Mapper<ValueInTimeDto, TemperatureRelationBo>,
    private val rainMapper: Mapper<ValueInTimeDto, RainRelationBo>,
    private val dayMerger: DayMerger,
) : Mapper<DayDto, DailyPredictionBo>() {
    override fun transform(data: DayDto): DailyPredictionBo =
        DailyPredictionBo(
            date = data.date,
            dayMerger.merge(
                temperatureList = data.temperature.map { temperatureMapper.transform(it) },
                rainList = data.rain.map { rainMapper.transform(it) },
            ),
        )
}
