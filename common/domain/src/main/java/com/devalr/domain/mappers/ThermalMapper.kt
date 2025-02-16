package com.devalr.domain.mappers

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.ThermalRelationBo

class ThermalMapper(
    private val timeMapper: Mapper<String, WeatherTime>
) : Mapper<ValueInTimeDto, ThermalRelationBo>() {
    override fun transform(data: ValueInTimeDto): ThermalRelationBo =
        ThermalRelationBo(
            thermalSensation = data.value
                .takeIf { it.isDigitsOnly() }
                ?.toInt() ?: 0,
            time = timeMapper.transform(data.time)
        )
}
