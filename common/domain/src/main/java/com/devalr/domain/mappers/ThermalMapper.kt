package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.model.WeatherTime
import com.devalr.domain.model.weather.ThermalRelationBo

class ThermalMapper(
    private val timeMapper: Mapper<String, WeatherTime>,
) : Mapper<ValueInTimeDto, ThermalRelationBo>() {
    override fun transform(data: ValueInTimeDto): ThermalRelationBo =
        ThermalRelationBo(
            thermalSensation = data.value.toInt(),
            time = timeMapper.transform(data.time),
        )
}
