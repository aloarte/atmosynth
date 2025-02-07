package com.devalr.domain.mappers

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.ValueInTimeDto
import com.devalr.domain.model.WeatherTime
import com.devalr.domain.model.weather.RainRelationBo

class RainMapper(
    private val timeMapper: Mapper<String, WeatherTime>,
) : Mapper<ValueInTimeDto, RainRelationBo>() {
    override fun transform(data: ValueInTimeDto): RainRelationBo =
        RainRelationBo(
            rainProbability = data.value.takeIf { it.isDigitsOnly() }?.toFloat() ?: 0f,
            time = timeMapper.transform(data.time),
        )
}
