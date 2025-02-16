package com.devalr.data.dto.dailyweather.common

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataOriginsDto(
    @SerialName("productor") val producer: String,
    @SerialName("web") val web: String,
    @SerialName("enlace") val link: String,
    @SerialName("language") val language: String,
    @SerialName("copyright") val copyright: String,
    @SerialName("notaLegal") val legalDisclaimer: String
)