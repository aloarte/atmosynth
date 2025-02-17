package com.devalr.dayweather.composables

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.now.NowWeatherDataVo


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DayWeatherContent(nowWeather: NowWeatherDataVo?, weatherByHours: List<HourlyDataVo>) {
    FlowRow(modifier = Modifier.padding(8.dp)) {
        nowWeather?.let {
            NowWeatherContent(nowStatus = it)

        }
        HourlyWeatherContent(weatherByHours)
    }

}


