package com.devalr.domain.mappers.hourly

import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.SkyState

class SkyMapper : Mapper<String, SkyState>() {
    override fun transform(data: String): SkyState =
        when (data) {
            "11n" -> SkyState.NightClear
            "12n" -> SkyState.NightLowClouds
            "14n" -> SkyState.NightVeryCloudy
            "13n" -> SkyState.NightVeryCloudy
            "15n" -> SkyState.NightVeryCloudy
            "16n" -> SkyState.NightCloudy
            "17n" -> SkyState.NightHighClouds
            "23n" -> SkyState.NightCloudySoftRain
            "24n" -> SkyState.NightCloudySoftRain
            "25n" -> SkyState.NightVeryCloudyRain
            "26n" -> SkyState.NightVeryCloudyRain
            "33n" -> SkyState.NightCloudySnow
            "34n" -> SkyState.NightCloudySnow
            "35n" -> SkyState.NightVeryCloudySnow
            "36n" -> SkyState.NightVeryCloudySnow
            "43n" -> SkyState.NightLowCloudyLowRain
            "44n" -> SkyState.NightLowCloudyLowRain
            "45n" -> SkyState.NightVeryCloudyLowRain
            "46n" -> SkyState.NightCloudySoftRain
            "51n" -> SkyState.NightCloudyStorm
            "52n" -> SkyState.NightCloudyStorm
            "53n" -> SkyState.NightVeryCloudyStorm
            "54n" -> SkyState.NightVeryCloudyStorm
            "61n" -> SkyState.NightCloudyStormSoftRain
            "62n" -> SkyState.NightCloudyStormSoftRain
            "63n" -> SkyState.NightCloudyStormSoftRain
            "64n" -> SkyState.NightCloudyStormSoftRain
            "71n" -> SkyState.NightCloudyLowSnow
            "72n" -> SkyState.NightCloudySnow
            "73n" -> SkyState.NightVeryCloudySnow
            "74n" -> SkyState.NightCloudyLowSnow
            "81n" -> SkyState.NightMists
            "82n" -> SkyState.NightMists
            "11" -> SkyState.DayClear
            "12" -> SkyState.DayLowClouds
            "13" -> SkyState.DayVeryCloudy
            "14" -> SkyState.DayVeryCloudy
            "15" -> SkyState.DayVeryCloudy
            "16" -> SkyState.DayCloudy
            "17" -> SkyState.DayHighClouds
            "23" -> SkyState.DayCloudySoftRain
            "24" -> SkyState.DayCloudySoftRain
            "25" -> SkyState.DayVeryCloudyRain
            "26" -> SkyState.DayVeryCloudyRain
            "43" -> SkyState.DayLowCloudyLowRain
            "44" -> SkyState.DayLowCloudyLowRain
            "45" -> SkyState.DayVeryCloudyLowRain
            "33" -> SkyState.DayCloudySnow
            "34" -> SkyState.DayCloudySnow
            "35" -> SkyState.DayVeryCloudySnow
            "36" -> SkyState.DayVeryCloudySnow
            "46" -> SkyState.DayCloudySoftRain
            "51" -> SkyState.DayCloudyStorm
            "52" -> SkyState.DayCloudyStorm
            "53" -> SkyState.DayVeryCloudyStorm
            "54" -> SkyState.DayVeryCloudyStorm
            "61" -> SkyState.DayCloudyStormSoftRain
            "62" -> SkyState.DayCloudyStormSoftRain
            "63" -> SkyState.DayCloudyStormSoftRain
            "64" -> SkyState.DayCloudyStormSoftRain
            "71" -> SkyState.DayCloudyLowSnow
            "72" -> SkyState.DayCloudySnow
            "73" -> SkyState.DayVeryCloudySnow
            "74" -> SkyState.DayCloudyLowSnow
            "81" -> SkyState.DayMists
            "82" -> SkyState.DayMists
            "83" -> SkyState.Haze
            else -> SkyState.Unknown

        }
}
