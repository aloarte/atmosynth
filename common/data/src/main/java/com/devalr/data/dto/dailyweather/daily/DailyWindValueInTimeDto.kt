package com.devalr.data.dto.dailyweather.daily

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWindValueInTimeDto(
    @SerialName("value") val value: String,
    @SerialName("periodo") val time: String? = null
)
