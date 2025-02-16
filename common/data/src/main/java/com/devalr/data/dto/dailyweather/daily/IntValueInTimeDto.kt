package com.devalr.data.dto.dailyweather.daily

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IntValueInTimeDto(
    @SerialName("value") val value: Int,
    @SerialName("hora") val time: Int
)
