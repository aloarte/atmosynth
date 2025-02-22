package com.devalr.dayweather.composables.weather.hourlycomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.dayweather.model.enums.HourlyEvent
import com.devalr.dayweather.model.enums.SkyStateIcon
import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.hourly.HourlyEventVo
import com.devalr.dayweather.model.hourly.HourlyWeatherVo
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme
import java.time.LocalDateTime

@Composable
fun HourlyWeatherContent(weatherByHours: List<HourlyDataVo>) {
    AtmosCard { paddingValues ->
        Column(modifier = Modifier.padding(15.dp)) {
            AtmosText(
                text = stringResource(R.string.now_weather_hourly_content_title),
                type = TextType.Title
            )
            LazyRow(modifier = Modifier.fillMaxSize()) {
                items(weatherByHours) {
                    when (it) {
                        is HourlyWeatherVo -> HourlyWeatherItem(it)
                        is HourlyEventVo -> HourlyEventItem(it)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HourlyWeatherContentPreview() {
    AtmosynthTheme {
        HourlyWeatherContent(
            weatherByHours = listOf(
                HourlyEventVo(
                    hour = "08:00",
                    event = HourlyEvent.Sunrise,
                    completeTime = LocalDateTime.now()
                ),
                HourlyWeatherVo(
                    hour = "09:00",
                    completeTime = LocalDateTime.now(),
                    skyState = SkyStateIcon.DayClear,
                    humidity = "50%",
                    precipitationProbability = "0%",
                    temperature = "20°C",
                    thermalSensation = "20°C"
                ),
                HourlyEventVo(
                    hour = "19:00",
                    event = HourlyEvent.Sunset,
                    completeTime = LocalDateTime.now()
                )
            )
        )
    }
}