package com.devalr.dayweather.composables.weather

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.composables.weather.hourlycomponents.HourlyWeatherContent
import com.devalr.dayweather.composables.weather.nowcomponents.DailySummaryContent
import com.devalr.dayweather.composables.weather.nowcomponents.NowWeatherContent
import com.devalr.dayweather.composables.weather.nowcomponents.NowWeatherHumidityContent
import com.devalr.dayweather.interactions.State
import com.devalr.dayweather.model.PromptStateVo
import com.devalr.dayweather.model.enums.HourlyEvent
import com.devalr.dayweather.model.hourly.HourlyEventVo
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.theme.AtmosynthTheme
import java.time.LocalDateTime

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DayWeatherContent(
    state: State,
    onDailySummaryPromptRetry: () -> Unit,
    onHumidityPressed: (Float) -> Unit
) {
    LazyColumn {
        item {
            FlowRow(modifier = Modifier.padding(8.dp)) {
                state.dailyWeather?.let {
                    NowWeatherHumidityContent(
                        humidityPercentage = it.humidity.current.toFloat(),
                        onHumidityPressed = onHumidityPressed
                    )
                    NowWeatherContent(nowStatus = it)
                }
                HourlyWeatherContent(weatherByHours = state.weatherByHours)
                DailySummaryContent(
                    loadingAiPrompt = state.promptSummary.loadingAiPrompt,
                    promptResult = state.promptSummary.promptResult,
                    onRetry = onDailySummaryPromptRetry
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DayWeatherContentPreview() {
    AtmosynthTheme {
        DayWeatherContent(
            state = State(
                dailyWeather = NowWeatherDataVo(
                    humidity = WeatherMaxMin("50", "22", "2"),
                    temperature = WeatherMaxMin("20", "22", "2"),
                    thermalSensation = WeatherMaxMin("20", "22", "2"),
                    skyAnimation = AnimationsType.WeatherRain
                ),
                weatherByHours = listOf(
                    HourlyEventVo(
                        hour = "08:00",
                        event = HourlyEvent.Sunrise,
                        completeTime = LocalDateTime.now()
                    ),
                    HourlyEventVo(
                        hour = "19:00",
                        event = HourlyEvent.Sunset,
                        completeTime = LocalDateTime.now()
                    )
                ),
                promptSummary = PromptStateVo(
                    promptResult = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
                    loadingAiPrompt = false
                ),
            ),
            onDailySummaryPromptRetry = {},
            onHumidityPressed = {})
    }

}


