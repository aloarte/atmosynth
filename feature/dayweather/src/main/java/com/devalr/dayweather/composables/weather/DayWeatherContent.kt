package com.devalr.dayweather.composables.weather

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.dayweather.composables.weather.hourlycomponents.HourlyWeatherContent
import com.devalr.dayweather.composables.weather.nowcomponents.DailySummaryContent
import com.devalr.dayweather.composables.weather.nowcomponents.NowWeatherContent
import com.devalr.dayweather.composables.weather.nowcomponents.NowWeatherHumidityContent
import com.devalr.dayweather.composables.weather.nowcomponents.NowWeatherWindContent
import com.devalr.dayweather.interactions.State
import com.devalr.dayweather.model.PromptStateVo
import com.devalr.dayweather.model.enums.HourlyEvent
import com.devalr.dayweather.model.enums.WindDirectionText
import com.devalr.dayweather.model.hourly.HourlyEventVo
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.dayweather.model.now.WindState
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.theme.AtmosynthTheme
import java.time.LocalDateTime

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DayWeatherContent(
    state: State,
    onDailySummaryPromptRetry: () -> Unit,
    onHumidityPressed: () -> Unit,
    onWindPressed: () -> Unit
) {
    LazyColumn {
        item {
            FlowRow(modifier = Modifier.padding(8.dp)) {
                state.dailyWeather?.let {
                    NowWeatherContent(nowStatus = it)
                }
                DailySummaryContent(
                    loadingAiPrompt = state.promptSummary.loadingAiPrompt,
                    promptResult = state.promptSummary.promptResult,
                    onRetry = onDailySummaryPromptRetry
                )
                HourlyWeatherContent(weatherByHours = state.weatherByHours)
                state.dailyWeather?.let {
                    Row {
                        NowWeatherHumidityContent(
                            humidityPercentage = it.humidity.current.toFloat(),
                            onHumidityPressed = onHumidityPressed
                        )
                        NowWeatherWindContent(
                            windState = it.wind,
                            onWindPressed = onWindPressed
                        )
                    }
                }
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
                    skyAnimation = AnimationsType.WeatherRain,
                    wind = WindState(WindDirectionText.W, 3)
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
                    promptResult = stringResource(R.string.lorep_ipsum),
                    loadingAiPrompt = false
                ),
            ),
            onDailySummaryPromptRetry = {},
            onHumidityPressed = {},
            onWindPressed = {})
    }

}


