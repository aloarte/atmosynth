package com.devalr.domain.mappers.daily

import com.devalr.data.dto.dailyweather.daily.DailyValueInTimeIntDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.weather.daily.PrecipitationProbability

class RainPrecipitationMapper(
    private val dayTimeMapper: Mapper<String, DayTime>
) : Mapper<DailyValueInTimeIntDto, PrecipitationProbability>() {
    override fun transform(data: DailyValueInTimeIntDto): PrecipitationProbability =
        PrecipitationProbability(
            probability = data.value,
            time = data.time?.let { dayTimeMapper.transform(it) } ?: DayTime.Unknown
        )
}
