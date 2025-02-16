package com.devalr.data.dto.dailyweather.daily

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyDto(
    @SerialName("probPrecipitacion") val rainProbability: List<DailyValueInTimeIntDto>,
    @SerialName("cotaNieveProv") val snowProbability: List<DailyValueInTimeStrDto>,
    @SerialName("estadoCielo") val skyState: List<DailySkyValueInTimeDto>,
    @SerialName("viento") val wind: List<DailyWindInTimeDto>,
    @SerialName("rachaMax") val windValue: List<DailyValueInTimeStrDto>,
    @SerialName("temperatura") val temperature: DailyMaxMinValuesDto,
    @SerialName("sensTermica") val thermalSensation: DailyMaxMinValuesDto,
    @SerialName("humedadRelativa") val relativeHumidity: DailyMaxMinValuesDto,
    @SerialName("fecha") val date: String? = null,
    @SerialName("uvMax") val uvMax: Int? = 0
)
