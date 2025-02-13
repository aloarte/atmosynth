package com.devalr.dayweather.composables

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.dayweather.model.SkyStateIcon


@Composable
fun HourlyWeatherItem(data: HourlyWeatherVo) {
    Column(modifier = Modifier.width(80.dp), verticalArrangement = Arrangement.Center) {
        Text(data.hour)
        Text(data.temperature)
        Text(data.thermalSensation)
        Text(data.humidity)
        Text(data.snowProbability)
        Text(data.rainProbability)
        WeatherImage(data.skyState)
    }
}

@Composable
fun WeatherImage(skyStateIcon: SkyStateIcon) {
    AsyncImage(
        model = "https://openweathermap.org/img/wn/${skyStateIcon.pngValue}@2x.png",
        contentDescription = null,
    )

}