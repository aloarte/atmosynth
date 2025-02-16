package com.devalr.domain.mappers.daily

import com.devalr.data.dto.dailyweather.daily.DailyMaxMinValuesDto
import com.devalr.data.dto.dailyweather.daily.IntValueInTimeDto
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.weather.daily.MaxMinValueBo
import com.devalr.domain.model.weather.daily.ValuesDayTimeBo

class MinMaxValueMapper(
    private val dailyValuesInTimeMapper: Mapper<IntValueInTimeDto, ValuesDayTimeBo>
) : Mapper<DailyMaxMinValuesDto, MaxMinValueBo>() {
    override fun transform(data: DailyMaxMinValuesDto): MaxMinValueBo =
        MaxMinValueBo(
            max = data.max,
            min = data.min,
            valuesPerDayTime = data.values.map { dailyValuesInTimeMapper.transform(it) }
        )
}
