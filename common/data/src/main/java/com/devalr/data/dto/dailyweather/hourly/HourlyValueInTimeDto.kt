package com.devalr.data.dto.dailyweather.hourly

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyValueInTimeDto(
    @SerialName("value") val value: String,
    @SerialName("periodo") val time: String
)
