package com.devalr.dayweather.mappers

import com.devalr.dayweather.mappers.AnimationSkyEnumMapper.Params
import com.devalr.domain.mappers.Mapper
import com.devalr.domain.model.enums.SkyState
import com.devalr.framework.enums.AnimationsType

class AnimationSkyEnumMapper : Mapper<Params, AnimationsType>() {

    data class Params(val temperature: Int, val windValue: Int, val skyState: SkyState)

    override fun transform(data: Params): AnimationsType = if (data.temperature < 0) {
        AnimationsType.WeatherCold
    } else if (data.windValue > 80) {
        AnimationsType.WeatherWind
    } else {
        when (data.skyState) {
            // Cold/snow section
            SkyState.NightCloudyLowSnow,
            SkyState.NightVeryCloudySnow,
            SkyState.NightCloudySnow,
            SkyState.DayCloudyLowSnow,
            SkyState.DayCloudySnow,
            SkyState.DayVeryCloudySnow -> AnimationsType.WeatherCold
            // Cloudy section
            SkyState.DayLowClouds,
            SkyState.NightLowClouds,
            SkyState.NightHighClouds,
            SkyState.NightVeryCloudy,
            SkyState.NightCloudy,
            SkyState.DayHighClouds,
            SkyState.DayVeryCloudy,
            SkyState.DayCloudy -> AnimationsType.WeatherClouds
            // Storm+Rain section
            SkyState.NightCloudyStorm,
            SkyState.NightCloudyStormSoftRain,
            SkyState.NightVeryCloudyStorm,
            SkyState.DayCloudyStormSoftRain,
            SkyState.DayCloudyStorm,
            SkyState.DayVeryCloudyStorm -> AnimationsType.WeatherRain
            // Rain section
            SkyState.NightCloudySoftRain,
            SkyState.NightLowCloudyLowRain,
            SkyState.NightVeryCloudyLowRain,
            SkyState.NightVeryCloudyRain,
            SkyState.DayCloudySoftRain,
            SkyState.DayLowCloudyLowRain,
            SkyState.DayVeryCloudyLowRain,
            SkyState.DayVeryCloudyRain -> AnimationsType.WeatherRain
            // Clear section
            SkyState.NightClear -> AnimationsType.WeatherClearNight
            SkyState.DayClear -> AnimationsType.WeatherClearDay
            // Use other data section
            SkyState.NightMists,
            SkyState.DayMists,
            SkyState.Unknown,
            SkyState.Haze -> {
                if (data.temperature < 5) {
                    AnimationsType.WeatherCold
                } else if (data.windValue > 40) {
                    AnimationsType.WeatherWind
                } else {
                    AnimationsType.WeatherClouds
                }
            }
        }
    }

}