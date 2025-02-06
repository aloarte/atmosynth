package com.devalr.domain.mappers

import com.devalr.domain.model.SkyState

class SkyMapper : Mapper<String, SkyState>() {
    override fun transform(data: String): SkyState =
        when (data) {
            "n11" -> SkyState.NIGHT_CLEAR
            "n12" -> SkyState.NIGHT_LOW_CLOUDS
            "n15" -> SkyState.NIGHT_VERY_CLOUDY
            "n16" -> SkyState.NIGHT_CLOUDY
            "n17" -> SkyState.NIGHT_HIGH_CLOUDS
            "n46" -> SkyState.NIGHT_CLOUDY_SOFT_RAIN
            "n64" -> SkyState.NIGHT_CLOUDY_STORM_SOFT_RAIN
            "11" -> SkyState.DAY_CLEAR
            "12" -> SkyState.DAY_LOW_CLOUDS
            "15" -> SkyState.DAY_VERY_CLOUDY
            "16" -> SkyState.DAY_CLOUDY
            "17" -> SkyState.DAY_HIGH_CLOUDS
            "46" -> SkyState.DAY_CLOUDY_SOFT_RAIN
            "64" -> SkyState.DAY_CLOUDY_STORM_SOFT_RAIN
            else -> SkyState.UNKNOWN
        }
}
