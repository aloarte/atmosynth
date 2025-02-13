package com.devalr.dayweather.mappers

import com.devalr.dayweather.model.SkyStateIcon
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.SkyState

class SkyEnumMapper : Mapper<SkyState, SkyStateIcon>() {
    override fun transform(data: SkyState): SkyStateIcon = when (data) {
        SkyState.UNKNOWN -> SkyStateIcon.DaySnow
        SkyState.NIGHT_CLEAR -> SkyStateIcon.NightClear
        SkyState.NIGHT_LOW_CLOUDS -> SkyStateIcon.NightFewClouds
        SkyState.NIGHT_VERY_CLOUDY -> SkyStateIcon.NightScatteredClouds
        SkyState.NIGHT_CLOUDY -> SkyStateIcon.NightBrokenClouds
        SkyState.NIGHT_HIGH_CLOUDS -> SkyStateIcon.NightFewClouds
        SkyState.NIGHT_CLOUDY_SOFT_RAIN -> SkyStateIcon.NightRain
        SkyState.NIGHT_CLOUDY_STORM_SOFT_RAIN -> SkyStateIcon.NightThunderstorm
        SkyState.NIGHT_MISTS -> SkyStateIcon.NightMist
        SkyState.DAY_CLEAR -> SkyStateIcon.DayClear
        SkyState.DAY_LOW_CLOUDS -> SkyStateIcon.DayFewClouds
        SkyState.DAY_VERY_CLOUDY -> SkyStateIcon.DayScatteredClouds
        SkyState.DAY_CLOUDY -> SkyStateIcon.DayBrokenClouds
        SkyState.DAY_HIGH_CLOUDS -> SkyStateIcon.DayFewClouds
        SkyState.DAY_CLOUDY_SOFT_RAIN -> SkyStateIcon.DayRain
        SkyState.DAY_CLOUDY_STORM_SOFT_RAIN -> SkyStateIcon.DayThunderstorm
        SkyState.DAY_MISTS -> SkyStateIcon.DayMist
    }
}