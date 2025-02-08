package com.devalr.domain.mappers

import com.devalr.domain.model.SkyState

class SkyMapper : Mapper<String, SkyState>() {
    override fun transform(data: String): SkyState =
        when (data) {
            "11n" -> SkyState.NIGHT_CLEAR
            "12n" -> SkyState.NIGHT_LOW_CLOUDS
            "15n" -> SkyState.NIGHT_VERY_CLOUDY
            "16n" -> SkyState.NIGHT_CLOUDY
            "17n" -> SkyState.NIGHT_HIGH_CLOUDS
            "46n" -> SkyState.NIGHT_CLOUDY_SOFT_RAIN
            "64n" -> SkyState.NIGHT_CLOUDY_STORM_SOFT_RAIN
            "82n" -> SkyState.NIGHT_MISTS
            "11" -> SkyState.DAY_CLEAR
            "12" -> SkyState.DAY_LOW_CLOUDS
            "15" -> SkyState.DAY_VERY_CLOUDY
            "16" -> SkyState.DAY_CLOUDY
            "17" -> SkyState.DAY_HIGH_CLOUDS
            "46" -> SkyState.DAY_CLOUDY_SOFT_RAIN
            "64" -> SkyState.DAY_CLOUDY_STORM_SOFT_RAIN
            "82" -> SkyState.DAY_MISTS
            else -> SkyState.UNKNOWN
        }
}
