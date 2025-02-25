package com.devalr.dayweather

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.devalr.dayweather.composables.details.DetailHumidityBottomSheet
import com.devalr.dayweather.composables.details.DetailUvBottomSheet
import com.devalr.dayweather.composables.details.DetailWindBottomSheet
import com.devalr.dayweather.composables.weather.DayWeatherContent
import com.devalr.dayweather.interactions.Event.OnRetryDailySummaryPrompt
import com.devalr.dayweather.interactions.Event.OnStartHumidityDetail
import com.devalr.dayweather.interactions.Event.OnStartUvDetail
import com.devalr.dayweather.interactions.Event.OnStartWindDetail
import com.devalr.dayweather.interactions.Event.OnUploadHumidityDetailVisibility
import com.devalr.dayweather.interactions.Event.OnUploadUvDetailVisibility
import com.devalr.dayweather.interactions.Event.OnUploadWindDetailVisibility
import com.devalr.dayweather.interactions.State
import com.devalr.framework.screens.ErrorScreen
import com.devalr.framework.screens.LoadingScreen
import org.koin.compose.koinInject

@Composable
fun WeatherScreen(viewModel: DayWeatherViewModel = koinInject()) {
    val state by viewModel.state.collectAsState()
    Column {
        if (state.loadingStates.loadingWeather) {
            LoadingScreen()
        } else {
            if (state.weatherByHours.isEmpty()) {
                ErrorScreen(
                    errorMessage = "No se pudieron recuperar los datos sobre el tiempo en la ciudad seleccionada.",
                    buttonMessage = "Reintentar"
                ) {

                }
            } else {
                BottomSheetViews(viewModel = viewModel, state = state)
                DayWeatherContent(
                    state = state,
                    onDailySummaryPromptRetry = {
                        viewModel.launchEvent(OnRetryDailySummaryPrompt)
                    },
                    onHumidityPressed = {
                        viewModel.launchEvent(
                            OnStartHumidityDetail(
                                state.dailyWeather?.humidity,
                                state.dailyWeather?.temperature
                            )
                        )

                    },
                    onWindPressed = {
                        viewModel.launchEvent(OnStartWindDetail(state.dailyWeather?.wind))
                    },
                    onUvPressed = {
                        viewModel.launchEvent(OnStartUvDetail(state.dailyWeather?.uvValue ?: "0"))
                    }
                )
            }
        }
    }
}


@Composable
private fun BottomSheetViews(viewModel: DayWeatherViewModel, state: State) {
    if (state.loadingStates.displayHumidityDetail) {
        state.dailyWeather?.humidity?.let {
            DetailHumidityBottomSheet(
                humidity = it,
                humidityPrompt = state.promptHumidity,
                onDismiss = {
                    viewModel.launchEvent(
                        OnUploadHumidityDetailVisibility(isVisible = false)
                    )
                }
            )
        }
    }
    if (state.loadingStates.displayWindDetail) {
        state.dailyWeather?.wind?.let {
            DetailWindBottomSheet(
                wind = it,
                windPrompt = state.promptWind,
                onDismiss = {
                    viewModel.launchEvent(
                        OnUploadWindDetailVisibility(isVisible = false)
                    )
                }
            )
        }
    }
    if (state.loadingStates.displayUvDetail) {
        state.dailyWeather?.uvValue?.let {
            DetailUvBottomSheet(
                uv = it,
                uvPrompt = state.promptUv,
                onDismiss = {
                    viewModel.launchEvent(
                        OnUploadUvDetailVisibility(isVisible = false)
                    )
                }
            )
        }
    }
}
