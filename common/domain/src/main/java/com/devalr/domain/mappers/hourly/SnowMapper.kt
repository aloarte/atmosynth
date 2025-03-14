package com.devalr.domain.mappers.hourly

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.hourly.HourlyValueInTimeDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.WeatherTime
import com.devalr.domain.model.weather.common.SnowRelationBo

class SnowMapper(
    private val timeMapper: Mapper<String, WeatherTime>
) : Mapper<HourlyValueInTimeDto, SnowRelationBo>() {
    override fun transform(data: HourlyValueInTimeDto): SnowRelationBo =
        SnowRelationBo(
            snowProbability = data.value
                .takeIf { it.isDigitsOnly() }
                ?.toFloat() ?: 0f,
            time = timeMapper.transform(data.time)
        )
}
