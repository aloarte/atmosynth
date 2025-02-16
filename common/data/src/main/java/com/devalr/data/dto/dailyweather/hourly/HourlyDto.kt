package com.devalr.data.dto.dailyweather.hourly

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HourlyDto(
    @SerialName("estadoCielo") val skyState: List<HourlySkyValueInTimeDto>,
    @SerialName("precipitacion") val rain: List<HourlyValueInTimeDto>,
    @SerialName("probPrecipitacion") val rainProbability: List<HourlyValueInTimeDto>,
    @SerialName("probTormenta") val stormProbability: List<HourlyValueInTimeDto>,
    @SerialName("nieve") val snow: List<HourlyValueInTimeDto>,
    @SerialName("probNieve") val snowProbability: List<HourlyValueInTimeDto>,
    @SerialName("temperatura") val temperature: List<HourlyValueInTimeDto>,
    @SerialName("sensTermica") val thermalSensation: List<HourlyValueInTimeDto>,
    @SerialName("humedadRelativa") val relativeHumidity: List<HourlyValueInTimeDto>,
    @SerialName("vientoAndRachaMax") val wind: List<HourlyWindInTimeDto>,
    @SerialName("fecha") val date: String,
    @SerialName("orto") val sunriseTime: String,
    @SerialName("ocaso") val sunsetTime: String
)
