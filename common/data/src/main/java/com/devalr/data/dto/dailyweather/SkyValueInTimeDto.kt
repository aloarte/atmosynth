package com.devalr.data.dto.dailyweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SkyValueInTimeDto(
    @SerialName("value") val value: String,
    @SerialName("periodo") val time: String,
    @SerialName("descripcion") val description: String,
)
