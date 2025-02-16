package com.devalr.domain.mappers

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.hourly.HourlyValueInTimeDto
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.HumidityRelationBo

class HumidityMapper(
    private val timeMapper: Mapper<String, WeatherTime>
) : Mapper<HourlyValueInTimeDto, HumidityRelationBo>() {
    override fun transform(data: HourlyValueInTimeDto): HumidityRelationBo =
        HumidityRelationBo(
            humidity = data.value
                .takeIf { it.isDigitsOnly() }
                ?.toInt() ?: 0,
            time = timeMapper.transform(data.time)
        )
}
