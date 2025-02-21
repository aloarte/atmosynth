package com.devalr.atmosynth

sealed class NavScreen(val route: String) {
    data object CitySelection : NavScreen("CitySelection")
    data object DayWeather : NavScreen("DayWeather")
}