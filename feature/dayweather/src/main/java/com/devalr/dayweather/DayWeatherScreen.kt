package com.devalr.dayweather

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.devalr.dayweather.composables.HourlyEventItem
import com.devalr.dayweather.composables.HourlyWeatherItem
import com.devalr.dayweather.interactions.Event.OnUploadLoadingState
import com.devalr.dayweather.mergers.HourlyMerger
import com.devalr.dayweather.model.HourlyDataVo
import com.devalr.dayweather.model.HourlyEventVo
import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.domain.repositories.GeminiRepository
import com.devalr.domain.repositories.WeatherRepository
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.screens.ErrorScreen
import com.devalr.framework.screens.LoadingScreen
import io.mockk.mockk
import org.koin.compose.koinInject
import org.koin.core.context.startKoin
import org.koin.dsl.module

@Composable
fun DayWeatherScreen(viewModel: DayWeatherViewModel = koinInject()) {
    val state by viewModel.state.collectAsState()
    Column {
        if (state.loadingWeather) {
            LoadingScreen()
        } else {
            if (state.weatherByHours.isEmpty() ) {
                ErrorScreen(
                    errorMessage = "No se pudieron recuperar los datos sobre el tiempo en la ciudad seleccionada.",
                    buttonMessage = "Reintentar"
                ) {

                }
            } else {
                DayWeatherContent(state.weatherByHours)
            }
        }
    }
}

@Composable
fun DayWeatherContent(weatherByHours: List<HourlyDataVo>) {
    AtmosAnimation(AnimationsType.WeatherCold)

    LazyRow {
        items(weatherByHours) {
            when (it) {
                is HourlyWeatherVo -> HourlyWeatherItem(it)
                is HourlyEventVo -> HourlyEventItem(it)
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun BakingScreenPreview() {
    startKoin {
        modules(module {
            single { mockk<GeminiRepository>() }
            single { mockk<WeatherRepository>() }
            single { mockk<HourlyMerger>() }
        })
    }
    val vm = DayWeatherViewModel(
        geminiRepository = koinInject(),
        weatherRepository = koinInject(),
        hourlyMerger = koinInject()
    )
    DayWeatherScreen(vm)
    vm.handleEvent(OnUploadLoadingState(true))

}
