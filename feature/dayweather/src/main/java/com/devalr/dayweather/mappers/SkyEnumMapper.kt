package com.devalr.dayweather.mappers

import com.devalr.dayweather.model.enums.SkyStateIcon
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.SkyState

class SkyEnumMapper : Mapper<SkyState, SkyStateIcon>() {
    override fun transform(data: SkyState): SkyStateIcon = when (data) {
        SkyState.Unknown -> SkyStateIcon.DayClear
        SkyState.NightClear -> SkyStateIcon.NightClear
        SkyState.NightLowClouds -> SkyStateIcon.NightFewClouds
        SkyState.NightVeryCloudy -> SkyStateIcon.NightScatteredClouds
        SkyState.NightCloudy -> SkyStateIcon.NightBrokenClouds
        SkyState.NightHighClouds -> SkyStateIcon.NightFewClouds
        SkyState.NightCloudySoftRain -> SkyStateIcon.NightShowerRain
        SkyState.NightCloudyStormSoftRain -> SkyStateIcon.NightThunderstorm
        SkyState.NightMists -> SkyStateIcon.NightMist
        SkyState.NightCloudyLowSnow -> SkyStateIcon.NightSnow
        SkyState.NightCloudySnow -> SkyStateIcon.NightSnow
        SkyState.NightCloudyStorm -> SkyStateIcon.NightThunderstorm
        SkyState.NightLowCloudyLowRain -> SkyStateIcon.NightShowerRain
        SkyState.NightVeryCloudyLowRain -> SkyStateIcon.NightShowerRain
        SkyState.NightVeryCloudyRain -> SkyStateIcon.NightRain
        SkyState.NightVeryCloudySnow -> SkyStateIcon.NightSnow
        SkyState.NightVeryCloudyStorm -> SkyStateIcon.NightThunderstorm
        SkyState.DayClear -> SkyStateIcon.DayClear
        SkyState.DayLowClouds -> SkyStateIcon.DayFewClouds
        SkyState.DayVeryCloudy -> SkyStateIcon.DayScatteredClouds
        SkyState.DayCloudy -> SkyStateIcon.DayBrokenClouds
        SkyState.DayHighClouds -> SkyStateIcon.DayFewClouds
        SkyState.DayCloudySoftRain -> SkyStateIcon.DayShowerRain
        SkyState.DayCloudyStormSoftRain -> SkyStateIcon.DayThunderstorm
        SkyState.DayMists -> SkyStateIcon.DayMist
        SkyState.DayCloudyLowSnow -> SkyStateIcon.DaySnow
        SkyState.DayCloudySnow -> SkyStateIcon.DaySnow
        SkyState.DayCloudyStorm -> SkyStateIcon.DayThunderstorm
        SkyState.DayLowCloudyLowRain -> SkyStateIcon.DayShowerRain
        SkyState.DayVeryCloudyLowRain -> SkyStateIcon.DayShowerRain
        SkyState.DayVeryCloudyRain -> SkyStateIcon.DayRain
        SkyState.DayVeryCloudySnow -> SkyStateIcon.DaySnow
        SkyState.DayVeryCloudyStorm -> SkyStateIcon.DayThunderstorm
        SkyState.Haze -> SkyStateIcon.DayClear
    }
}