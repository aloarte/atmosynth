package com.devalr.data.dto.dailyweather.daily

import com.devalr.data.dto.dailyweather.common.DataOriginsDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherDto(
    @SerialName("origen") val origen: DataOriginsDto,
    @SerialName("elaborado") val made: String,
    @SerialName("nombre") val name: String,
    @SerialName("provincia") val province: String,
    @SerialName("prediccion") val prediction: DailyPredictionDto,
    @SerialName("id") val id: Int,
    @SerialName("version") val version: Float
)