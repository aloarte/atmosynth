package com.devalr.data.dto.dailyweather.hourly

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWindInTimeDto(
    @SerialName("direccion") val direction: List<String>? = null,
    @SerialName("velocidad") val speed: List<String>? = null,
    @SerialName("value") val value: String? = null,
    @SerialName("periodo") val time: String
)
