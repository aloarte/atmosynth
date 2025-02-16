package com.devalr.domain.mappers.daily

import com.devalr.data.dto.dailyweather.daily.IntValueInTimeDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.DayTime
import com.devalr.domain.model.weather.daily.ValuesDayTimeBo

class DailyValuesInTimeMapper(
    private val intDayTimeMapper: Mapper<Int, DayTime>
) : Mapper<IntValueInTimeDto, ValuesDayTimeBo>() {
    override fun transform(data: IntValueInTimeDto): ValuesDayTimeBo =
        ValuesDayTimeBo(
            value = data.value,
            time = intDayTimeMapper.transform(data.time)
        )
}
