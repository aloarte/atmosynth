package com.devalr.domain.mappers.daily

import com.devalr.data.dto.dailyweather.daily.DailyWindInTimeDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.WindDirection
import com.devalr.domain.model.weather.daily.DailyWindState

class WindStateMapper(
    private val windDirectionMapper: Mapper<String, WindDirection>,
    private val stringDayTimeMapper: Mapper<String, DayTime>

) : Mapper<DailyWindInTimeDto, DailyWindState>() {
    override fun transform(data: DailyWindInTimeDto): DailyWindState =
        DailyWindState(
            direction = data.direction?.let { windDirectionMapper.transform(it) }
                ?: WindDirection.None,
            speed = data.speed,
            time = data.time?.let { stringDayTimeMapper.transform(it) } ?: DayTime.Unknown
        )
}
