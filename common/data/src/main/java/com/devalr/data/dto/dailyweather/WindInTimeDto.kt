package com.devalr.data.dto.dailyweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WindInTimeDto(
    @SerialName("direccion") val direction: List<String>? = null,
    @SerialName("velocidad") val speed: List<String>? = null,
    @SerialName("value") val value: String? = null,
    @SerialName("periodo") val time: String,
)
