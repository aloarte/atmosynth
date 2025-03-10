package com.devalr.domain.mappers.hourly

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.hourly.HourlyValueInTimeDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.common.ThermalRelationBo

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
