package com.devalr.dayweather.model

enum class SkyStateIcon(val pngValue: String) {
    NightClear("01n"),
    NightFewClouds("02n"),
    NightScatteredClouds("03n"),
    NightBrokenClouds("04n"),
    NightShowerRain("09n"),
    NightRain("10n"),
    NightThunderstorm("11n"),
    NightSnow("13n"),
    NightMist("50n"),
    DayClear("01d"),
    DayFewClouds("02d"),
    DayScatteredClouds("03d"),
    DayBrokenClouds("04d"),
    DayShowerRain("09d"),
    DayRain("10d"),
    DayThunderstorm("11d"),
    DaySnow("13d"),
    DayMist("50d")
}