package com.devalr.data.dto.dailyweather.daily

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWindInTimeDto(
    @SerialName("direccion") val direction: String? = null,
    @SerialName("velocidad") val speed: Int,
    @SerialName("periodo") val time: String? =null
)
