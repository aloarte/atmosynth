package com.devalr.domain.mappers

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.hourly.HourlyValueInTimeDto
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.common.RainRelationBo

class RainMapper(
    private val timeMapper: Mapper<String, WeatherTime>
) : Mapper<HourlyValueInTimeDto, RainRelationBo>() {
    override fun transform(data: HourlyValueInTimeDto): RainRelationBo =
        RainRelationBo(
            rainProbability = data.value
                .takeIf { it.isDigitsOnly() }
                ?.toFloat() ?: 0f,
            time = timeMapper.transform(data.time)
        )
}
