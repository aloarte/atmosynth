package com.devalr.domain.mappers.params

import com.devalr.data.dto.dailyweather.hourly.HourlyValueInTimeDto

data class ValueInTimeParams(
    val data: HourlyValueInTimeDto,
    val date: String
)
