package com.devalr.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyWeatherDto(
    @SerialName("origen") val origen: DataOrigins,
    @SerialName("elaborado") val made: String,
    @SerialName("nombre") val name: String,
    @SerialName("provincia") val province: String,
    @SerialName("prediccion") val prediction: Prediction,
    @SerialName("id") val id: String,
    @SerialName("version") val version: String,
) {
    @Serializable
    data class DataOrigins(
        @SerialName("productor") val producer: String,
        @SerialName("web") val web: String,
        @SerialName("enlace") val link: String,
        @SerialName("language") val language: String,
        @SerialName("copyright") val copyright: String,
        @SerialName("notaLegal") val legalDisclaimer: String,
    )

    @Serializable
    data class Prediction(
        @SerialName("dia") val day: List<Day>,
    )

    @Serializable
    data class Day(
        @SerialName("estadoCielo") val skyState: List<SkyValueInTime>,
        @SerialName("precipitacion") val rain: List<ValueInTime>,
        @SerialName("probPrecipitacion") val rainProbability: List<ValueInTime>,
        @SerialName("probTormenta") val stormProbability: List<ValueInTime>,
        @SerialName("nieve") val snow: List<ValueInTime>,
        @SerialName("probNieve") val snowProbability: List<ValueInTime>,
        @SerialName("temperatura") val temperature: List<ValueInTime>,
        @SerialName("sensTermica") val thermalSensation: List<ValueInTime>,
        @SerialName("humedadRelativa") val relativeHumidity: List<ValueInTime>,
        @SerialName("vientoAndRachaMax") val wind: List<WindInTime>,
        @SerialName("fecha") val date: String,
        @SerialName("orto") val sunriseTime: String,
        @SerialName("ocaso") val sunsetTime: String,
    )

    @Serializable
    data class SkyValueInTime(
        @SerialName("value") val value: String,
        @SerialName("periodo") val time: String,
        @SerialName("descripcion") val description: String,
    )

    @Serializable
    data class ValueInTime(
        @SerialName("value") val value: String,
        @SerialName("periodo") val time: String,
    )

    @Serializable
    data class WindInTime(
        @SerialName("direccion") val direction: List<String>? = null,
        @SerialName("velocidad") val speed: List<String>? = null,
        @SerialName("value") val value: String? = null,
        @SerialName("periodo") val time: String,
    )
}
