package com.devalr.domain.mappers.daily

import androidx.core.text.isDigitsOnly
import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeStrDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.weather.daily.PrecipitationProbability

class SnowPrecipitationMapper(
    private val dayTimeMapper: Mapper<String, DayTime>
) : Mapper<DailyValueInTimeStrDto, PrecipitationProbability>() {
    override fun transform(data: DailyValueInTimeStrDto): PrecipitationProbability =
        PrecipitationProbability(
            probability = data.value
                .takeIf { it.isDigitsOnly() && it.isNotEmpty() }
                ?.toFloat()?.takeIf { it <= 100F } ?: 0f,
            time = data.time?.let { dayTimeMapper.transform(it) } ?: DayTime.Unknown
        )
}
