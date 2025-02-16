package com.devalr.data.dto.dailyweather.hourly

import com.devalr.data.dto.dailyweather.common.DataOriginsDto
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDto(
    @SerialName("origen") val origen: DataOriginsDto,
    @SerialName("elaborado") val made: String,
    @SerialName("nombre") val name: String,
    @SerialName("provincia") val province: String,
    @SerialName("prediccion") val prediction: HourlyPredictionDto,
    @SerialName("id") val id: String,
    @SerialName("version") val version: String
)