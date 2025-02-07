package com.devalr.domain.mappers

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.model.WeatherTime
import com.devalr.domain.model.weather.TemperatureRelationBo

class TemperatureMapper(
    private val timeMapper: Mapper<String, WeatherTime>,
) : Mapper<ValueInTimeDto, TemperatureRelationBo>() {
    override fun transform(data: ValueInTimeDto): TemperatureRelationBo =
        TemperatureRelationBo(
            temperature = data.value.takeIf { it.isDigitsOnly() }?.toInt() ?: 0,
            time = timeMapper.transform(data.time),
        )
}
