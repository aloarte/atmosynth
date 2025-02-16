package com.devalr.dayweather.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.model.HourlyDataVo
import com.devalr.dayweather.model.HourlyEventVo
import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DayWeatherContent(weatherByHours: List<HourlyDataVo>) {
    FlowRow(modifier = Modifier.padding(8.dp)) {
        NowWeatherContent()
        NowWeatherContent()
        NowWeatherContent()
        NowWeatherContent()

        HourlyWeatherContent(weatherByHours)
    }

}

@Composable
fun NowWeatherContent() {
    AtmosCard(width = 200.dp) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            AtmosAnimation(AnimationsType.WeatherClear)
        }
    }
}


@Composable
fun HourlyWeatherContent(weatherByHours: List<HourlyDataVo>) {
    AtmosCard {
        Row {
            AtmosSeparator(size = 10.dp, type = SeparatorType.Horizontal)
            AtmosText(text = "Tiempo por horas", type = TextType.Title)
        }
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


