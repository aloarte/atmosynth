package com.devalr.data.dto.dailyweather.daily

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailySkyValueInTimeDto(
    @SerialName("value") val value: String,
    @SerialName("periodo") val time: String? = null,
    @SerialName("descripcion") val description: String
)
