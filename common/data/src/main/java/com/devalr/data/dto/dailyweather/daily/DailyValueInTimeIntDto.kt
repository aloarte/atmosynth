package com.devalr.data.dto.dailyweather.daily

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyValueInTimeIntDto(
    @SerialName("value") val value: Float,
    @SerialName("periodo") val time: String?=null
)
