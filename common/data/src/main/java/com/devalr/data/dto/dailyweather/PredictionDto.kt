package com.devalr.data.dto.dailyweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PredictionDto(
    @SerialName("dia") val day: List<DayDto>
)
