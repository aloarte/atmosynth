package com.devalr.domain.mappers

import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.model.WeatherTime
import com.devalr.domain.model.weather.SnowRelationBo

class SnowMapper(
    private val timeMapper: Mapper<String, WeatherTime>,
) : Mapper<ValueInTimeDto, SnowRelationBo>() {
    override fun transform(data: ValueInTimeDto): SnowRelationBo =
        SnowRelationBo(
            snowProbability = data.value.toFloat(),
            time = timeMapper.transform(data.time),
        )
}
