package com.devalr.domain.mappers

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.model.WeatherTime
import com.devalr.domain.model.weather.HumidityRelationBo

class HumidityMapper(
    private val timeMapper: Mapper<String, WeatherTime>
) : Mapper<ValueInTimeDto, HumidityRelationBo>() {
    override fun transform(data: ValueInTimeDto): HumidityRelationBo =
        HumidityRelationBo(
            humidity = data.value
                .takeIf { it.isDigitsOnly() }
                ?.toInt() ?: 0,
            time = timeMapper.transform(data.time)
        )
}
