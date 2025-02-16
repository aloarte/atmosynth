package com.devalr.data.dto.dailyweather.daily

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyPredictionDto(
    @SerialName("dia") val day: List<DailyDto>
)
