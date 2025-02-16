package com.devalr.domain.mappers.daily

import com.devalr.data.dto.dailyweather.daily.DailySkyValueInTimeDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.enums.SkyState
import com.devalr.domain.model.weather.daily.DailySkyState

class DailyStateMapper(
    private val dayTimeMapper: Mapper<String, DayTime>,
    private val dailySkyStateMapper: Mapper<String, SkyState>
) : Mapper<DailySkyValueInTimeDto, DailySkyState>() {
    override fun transform(data: DailySkyValueInTimeDto): DailySkyState =
        DailySkyState(
            state = data.value.let { dailySkyStateMapper.transform(it) },
            description = data.description,
            time = data.time?.let { dayTimeMapper.transform(it) } ?: DayTime.Unknown
        )
}
