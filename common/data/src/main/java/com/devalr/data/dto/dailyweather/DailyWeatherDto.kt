package com.devalr.data.dto.dailyweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherDto(
    @SerialName("origen") val origen: DataOrigins,
    @SerialName("elaborado") val made: String,
    @SerialName("nombre") val name: String,
    @SerialName("provincia") val province: String,
    @SerialName("prediccion") val prediction: PredictionDto,
    @SerialName("id") val id: String,
    @SerialName("version") val version: String
) {
    @Serializable
    data class DataOrigins(
        @SerialName("productor") val producer: String,
        @SerialName("web") val web: String,
        @SerialName("enlace") val link: String,
        @SerialName("language") val language: String,
        @SerialName("copyright") val copyright: String,
        @SerialName("notaLegal") val legalDisclaimer: String
    )
}
