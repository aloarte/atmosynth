package com.devalr.dayweather

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.devalr.dayweather.composables.weather.DayWeatherContent
import com.devalr.dayweather.interactions.Event.OnRetryDailySummaryPrompt
import com.devalr.framework.screens.ErrorScreen
import com.devalr.framework.screens.LoadingScreen
import org.koin.compose.koinInject

@Composable
fun WeatherScreen(
    viewModel: DayWeatherViewModel = koinInject(), onHumidityPressed: (Float) -> Unit
) {
    val state by viewModel.state.collectAsState()
    Column {
        if (state.loadingWeather) {
            LoadingScreen()
        } else {
            if (state.weatherByHours.isEmpty()) {
                ErrorScreen(
                    errorMessage = "No se pudieron recuperar los datos sobre el tiempo en la ciudad seleccionada.",
                    buttonMessage = "Reintentar"
                ) {

                }
            } else {
                DayWeatherContent(
                    state = state,
                    onDailySummaryPromptRetry = {
                        viewModel.launchEvent(OnRetryDailySummaryPrompt)
                    },
                    onHumidityPressed = onHumidityPressed
                )
            }
        }
    }
}
