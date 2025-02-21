package com.devalr.dayweather.navigation

sealed class NavScreen(val route: String) {
    data object HomeDayWeather : NavScreen("HomeDayWeather")
    data object DetailHumidity : NavScreen("DetailHumidity/{humidity}") {
        fun createRoute(humidity: Float): String {
            return "DetailHumidity/$humidity"
        }
    }
}

