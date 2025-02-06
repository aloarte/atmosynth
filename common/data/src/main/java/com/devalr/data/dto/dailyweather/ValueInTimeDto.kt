package com.devalr.data.dto.dailyweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ValueInTimeDto(
    @SerialName("value") val value: String,
    @SerialName("periodo") val time: String,
)
