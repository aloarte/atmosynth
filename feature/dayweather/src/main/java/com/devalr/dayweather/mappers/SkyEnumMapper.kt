package com.devalr.dayweather.mappers

import com.devalr.dayweather.model.enums.SkyStateIcon
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.SkyState

class SkyEnumMapper : Mapper<SkyState, SkyStateIcon>() {
    override fun transform(data: SkyState): SkyStateIcon = when (data) {
        SkyState.UNKNOWN -> SkyStateIcon.DayClear
        SkyState.NIGHT_CLEAR -> SkyStateIcon.NightClear
        SkyState.NIGHT_LOW_CLOUDS -> SkyStateIcon.NightFewClouds
        SkyState.NIGHT_VERY_CLOUDY -> SkyStateIcon.NightScatteredClouds
        SkyState.NIGHT_CLOUDY -> SkyStateIcon.NightBrokenClouds
        SkyState.NIGHT_HIGH_CLOUDS -> SkyStateIcon.NightFewClouds
        SkyState.NIGHT_CLOUDY_SOFT_RAIN -> SkyStateIcon.NightShowerRain
        SkyState.NIGHT_CLOUDY_STORM_SOFT_RAIN -> SkyStateIcon.NightThunderstorm
        SkyState.NIGHT_MISTS -> SkyStateIcon.NightMist
        SkyState.NIGHT_CLOUDY_LOW_SNOW -> SkyStateIcon.NightSnow
        SkyState.NIGHT_CLOUDY_SNOW -> SkyStateIcon.NightSnow
        SkyState.NIGHT_CLOUDY_STORM -> SkyStateIcon.NightThunderstorm
        SkyState.NIGHT_LOW_CLOUDY_LOW_RAIN -> SkyStateIcon.NightShowerRain
        SkyState.NIGHT_VERY_CLOUDY_LOW_RAIN -> SkyStateIcon.NightShowerRain
        SkyState.NIGHT_VERY_CLOUDY_RAIN -> SkyStateIcon.NightRain
        SkyState.NIGHT_VERY_CLOUDY_SNOW -> SkyStateIcon.NightSnow
        SkyState.NIGHT_VERY_CLOUDY_STORM -> SkyStateIcon.NightThunderstorm
        SkyState.DAY_CLEAR -> SkyStateIcon.DayClear
        SkyState.DAY_LOW_CLOUDS -> SkyStateIcon.DayFewClouds
        SkyState.DAY_VERY_CLOUDY -> SkyStateIcon.DayScatteredClouds
        SkyState.DAY_CLOUDY -> SkyStateIcon.DayBrokenClouds
        SkyState.DAY_HIGH_CLOUDS -> SkyStateIcon.DayFewClouds
        SkyState.DAY_CLOUDY_SOFT_RAIN -> SkyStateIcon.DayShowerRain
        SkyState.DAY_CLOUDY_STORM_SOFT_RAIN -> SkyStateIcon.DayThunderstorm
        SkyState.DAY_MISTS -> SkyStateIcon.DayMist
        SkyState.DAY_CLOUDY_LOW_SNOW -> SkyStateIcon.DaySnow
        SkyState.DAY_CLOUDY_SNOW -> SkyStateIcon.DaySnow
        SkyState.DAY_CLOUDY_STORM -> SkyStateIcon.DayThunderstorm
        SkyState.DAY_LOW_CLOUDY_LOW_RAIN -> SkyStateIcon.DayShowerRain
        SkyState.DAY_VERY_CLOUDY_LOW_RAIN -> SkyStateIcon.DayShowerRain
        SkyState.DAY_VERY_CLOUDY_RAIN -> SkyStateIcon.DayRain
        SkyState.DAY_VERY_CLOUDY_SNOW -> SkyStateIcon.DaySnow
        SkyState.DAY_VERY_CLOUDY_STORM -> SkyStateIcon.DayThunderstorm
        SkyState.HAZE -> SkyStateIcon.DayClear

    }
}