package com.devalr.domain.mappers

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.hourly.HourlyValueInTimeDto
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.ThermalRelationBo

class ThermalMapper(
    private val timeMapper: Mapper<String, WeatherTime>
) : Mapper<HourlyValueInTimeDto, ThermalRelationBo>() {
    override fun transform(data: HourlyValueInTimeDto): ThermalRelationBo =
        ThermalRelationBo(
            thermalSensation = data.value
                .takeIf { it.isDigitsOnly() }
                ?.toInt() ?: 0,
            time = timeMapper.transform(data.time)
        )
}
