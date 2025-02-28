package com.devalr.dayweather.composables.weather

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.composables.weather.hourlycomponents.HourlyWeatherContent
import com.devalr.dayweather.composables.weather.nowcomponents.NowWeatherContent
import com.devalr.dayweather.composables.weather.nowcomponents.humidity.NowWeatherHumidityContent
import com.devalr.dayweather.composables.weather.nowcomponents.precipitation.NowWeatherPrecipitationContent
import com.devalr.dayweather.composables.weather.nowcomponents.uv.NowWeatherUvContent
import com.devalr.dayweather.composables.weather.nowcomponents.wind.NowWeatherWindContent
import com.devalr.dayweather.interactions.State
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
    onDailySummaryPressed: () -> Unit,
    onHourlyPressed: () -> Unit,
    onPrecipitationPressed: () -> Unit,
    onHumidityPressed: () -> Unit,
    onWindPressed: () -> Unit,
    onUvPressed: () -> Unit
) {
    state.dailyWeather?.let { weather ->
        LazyColumn {
            item {
                FlowRow(modifier = Modifier.padding(8.dp)) {
                    NowWeatherContent(
                        nowStatus = weather,
                        onDailySummaryPressed = onDailySummaryPressed
                    )
                    HourlyWeatherContent(
                        weatherByHours = state.weatherByHours,
                        onHourlyPressed = onHourlyPressed
                    )
                    WeatherHalfCards(
                        weather = weather,
                        onPrecipitationPressed = onPrecipitationPressed,
                        onHumidityPressed = onHumidityPressed,
                        onWindPressed = onWindPressed,
                        onUvPressed = onUvPressed
                    )
                }

            }
        }
    }
}

@Composable
private fun WeatherHalfCards(
    weather: NowWeatherDataVo,
    onPrecipitationPressed: () -> Unit,
    onHumidityPressed: () -> Unit,
    onWindPressed: () -> Unit,
    onUvPressed: () -> Unit
) {
    Row {
        NowWeatherPrecipitationContent(
            rainPrecipitationProbability = weather.rainProbability.toFloat(),
            snowPrecipitationProbability = weather.snowProbability.toFloat(),
            onPrecipitationPressed = onPrecipitationPressed
        )
        NowWeatherHumidityContent(
            humidityPercentage = weather.humidity.current.toFloat(),
            onHumidityPressed = onHumidityPressed
        )

    }
    Row {
        NowWeatherWindContent(
            windState = weather.wind,
            onWindPressed = onWindPressed
        )
        NowWeatherUvContent(
            uvValue = weather.uvValue,
            onUvPressed = onUvPressed
        )
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
                    wind = WindState(WindDirectionText.W, speed = 3),
                    uvValue = "3",
                    snowProbability = "0",
                    rainProbability = "20"
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
            ),
            onDailySummaryPressed = {},
            onHourlyPressed = {},
            onPrecipitationPressed = {},
            onHumidityPressed = {},
            onWindPressed = {},
            onUvPressed = {}
        )
    }

}


