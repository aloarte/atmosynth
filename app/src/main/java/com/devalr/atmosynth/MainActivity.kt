package com.devalr.atmosynth

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.devalr.cityselector.compose.CitySelectorScreen
import com.devalr.dayweather.WeatherScreen
import com.devalr.framework.theme.AtmosynthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AtmosynthTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = NavScreen.DayWeather.route
                    ) {
                        composable(NavScreen.DayWeather.route) {
                            WeatherScreen{
                                navController.navigate(NavScreen.CitySelection.route)
                            }
                        }
                        composable(NavScreen.CitySelection.route) {
                            CitySelectorScreen()
                        }
                    }
                }
            }
        }
    }
}
