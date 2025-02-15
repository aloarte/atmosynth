package com.devalr.domain.mappers

import android.util.Log
import com.devalr.domain.model.SkyState

class SkyMapper : Mapper<String, SkyState>() {
    override fun transform(data: String): SkyState =
        when (data) {
            "11n" -> SkyState.NIGHT_CLEAR
            "12n" -> SkyState.NIGHT_LOW_CLOUDS
            "14n" -> SkyState.NIGHT_VERY_CLOUDY
            "15n" -> SkyState.NIGHT_VERY_CLOUDY
            "16n" -> SkyState.NIGHT_CLOUDY
            "17n" -> SkyState.NIGHT_HIGH_CLOUDS
            "23n" -> SkyState.NIGHT_CLOUDY_SOFT_RAIN
            "24n" -> SkyState.NIGHT_CLOUDY_SOFT_RAIN
            "25n" -> SkyState.NIGHT_VERY_CLOUDY_RAIN
            "26n" -> SkyState.NIGHT_VERY_CLOUDY_RAIN
            "33n" -> SkyState.NIGHT_CLOUDY_SNOW
            "34n" -> SkyState.NIGHT_CLOUDY_SNOW
            "35n" -> SkyState.NIGHT_VERY_CLOUDY_SNOW
            "36n" -> SkyState.NIGHT_VERY_CLOUDY_SNOW
            "43n" -> SkyState.NIGHT_LOW_CLOUDY_LOW_RAIN
            "44n" -> SkyState.NIGHT_LOW_CLOUDY_LOW_RAIN
            "45n" -> SkyState.NIGHT_VERY_CLOUDY_LOW_RAIN
            "46n" -> SkyState.NIGHT_CLOUDY_SOFT_RAIN
            "51n" -> SkyState.NIGHT_CLOUDY_STORM
            "52n" -> SkyState.NIGHT_CLOUDY_STORM
            "53n" -> SkyState.NIGHT_VERY_CLOUDY_STORM
            "54n" -> SkyState.NIGHT_VERY_CLOUDY_STORM
            "61n" -> SkyState.NIGHT_CLOUDY_STORM_SOFT_RAIN
            "62n" -> SkyState.NIGHT_CLOUDY_STORM_SOFT_RAIN
            "63n" -> SkyState.NIGHT_CLOUDY_STORM_SOFT_RAIN
            "64n" -> SkyState.NIGHT_CLOUDY_STORM_SOFT_RAIN
            "71n" -> SkyState.NIGHT_CLOUDY_LOW_SNOW
            "72n" -> SkyState.NIGHT_CLOUDY_SNOW
            "73n" -> SkyState.NIGHT_VERY_CLOUDY_SNOW
            "74n" -> SkyState.NIGHT_CLOUDY_LOW_SNOW
            "81n" -> SkyState.NIGHT_MISTS
            "82n" -> SkyState.NIGHT_MISTS
            "11" -> SkyState.DAY_CLEAR
            "12" -> SkyState.DAY_LOW_CLOUDS
            "14" -> SkyState.DAY_VERY_CLOUDY
            "15" -> SkyState.DAY_VERY_CLOUDY
            "16" -> SkyState.DAY_CLOUDY
            "17" -> SkyState.DAY_HIGH_CLOUDS
            "23" -> SkyState.DAY_CLOUDY_SOFT_RAIN
            "24" -> SkyState.DAY_CLOUDY_SOFT_RAIN
            "25" -> SkyState.DAY_VERY_CLOUDY_RAIN
            "26" -> SkyState.DAY_VERY_CLOUDY_RAIN
            "43" -> SkyState.DAY_LOW_CLOUDY_LOW_RAIN
            "44" -> SkyState.DAY_LOW_CLOUDY_LOW_RAIN
            "45" -> SkyState.DAY_VERY_CLOUDY_LOW_RAIN
            "33" -> SkyState.DAY_CLOUDY_SNOW
            "34" -> SkyState.DAY_CLOUDY_SNOW
            "35" -> SkyState.DAY_VERY_CLOUDY_SNOW
            "36" -> SkyState.DAY_VERY_CLOUDY_SNOW
            "46" -> SkyState.DAY_CLOUDY_SOFT_RAIN
            "51" -> SkyState.DAY_CLOUDY_STORM
            "52" -> SkyState.DAY_CLOUDY_STORM
            "53" -> SkyState.DAY_VERY_CLOUDY_STORM
            "54" -> SkyState.DAY_VERY_CLOUDY_STORM
            "61" -> SkyState.DAY_CLOUDY_STORM_SOFT_RAIN
            "62" -> SkyState.DAY_CLOUDY_STORM_SOFT_RAIN
            "63" -> SkyState.DAY_CLOUDY_STORM_SOFT_RAIN
            "64" -> SkyState.DAY_CLOUDY_STORM_SOFT_RAIN
            "71" -> SkyState.DAY_CLOUDY_LOW_SNOW
            "72" -> SkyState.DAY_CLOUDY_SNOW
            "73" -> SkyState.DAY_VERY_CLOUDY_SNOW
            "74" -> SkyState.DAY_CLOUDY_LOW_SNOW
            "81" -> SkyState.DAY_MISTS
            "82" -> SkyState.DAY_MISTS
            "83" -> SkyState.HAZE

            else -> {
                Log.d("ALRALR", "UNK $data")
                SkyState.UNKNOWN
            }
        }
}
