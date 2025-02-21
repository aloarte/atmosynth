package com.devalr.dayweather.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devalr.dayweather.WeatherScreen
import com.devalr.dayweather.composables.detailhumidity.DetailHumidityContent

@Composable
fun WeatherScreenNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavScreen.HomeDayWeather.route
    ) {
        composable(NavScreen.HomeDayWeather.route) {
            WeatherScreen(onHumidityPressed = { humidityValue ->
                navController.navigate(NavScreen.DetailHumidity.createRoute(humidityValue))
            })
        }
        composable(NavScreen.DetailHumidity.route) {
            val humidity = navController
                .currentBackStackEntry
                ?.arguments
                ?.getFloat("humidity")
                ?: 0f
            DetailHumidityContent(humidityPercentage = humidity) {
                navController.popBackStack()
            }
        }
    }
}