package com.devalr.data.dto.dailyweather.hourly

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyPredictionDto(
    @SerialName("dia") val day: List<HourlyDto>
)
