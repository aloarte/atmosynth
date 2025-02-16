package com.devalr.data.dto.dailyweather.daily

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DailyMaxMinValuesDto(
    @SerialName("minima") val min:Int,
    @SerialName("maxima") val max:Int,
    @SerialName("dato") val values:List<IntValueInTimeDto>
)