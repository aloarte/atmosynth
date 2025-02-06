package com.devalr.data.dto.dailyweather

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayDto(
    @SerialName("estadoCielo") val skyState: List<SkyValueInTimeDto>,
    @SerialName("precipitacion") val rain: List<ValueInTimeDto>,
    @SerialName("probPrecipitacion") val rainProbability: List<ValueInTimeDto>,
    @SerialName("probTormenta") val stormProbability: List<ValueInTimeDto>,
    @SerialName("nieve") val snow: List<ValueInTimeDto>,
    @SerialName("probNieve") val snowProbability: List<ValueInTimeDto>,
    @SerialName("temperatura") val temperature: List<ValueInTimeDto>,
    @SerialName("sensTermica") val thermalSensation: List<ValueInTimeDto>,
    @SerialName("humedadRelativa") val relativeHumidity: List<ValueInTimeDto>,
    @SerialName("vientoAndRachaMax") val wind: List<WindInTimeDto>,
    @SerialName("fecha") val date: String,
    @SerialName("orto") val sunriseTime: String,
    @SerialName("ocaso") val sunsetTime: String,
)
