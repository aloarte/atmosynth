package com.devalr.dayweather.composables.hourlycomponents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.hourly.HourlyEventVo
import com.devalr.dayweather.model.hourly.HourlyWeatherVo
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.TextType

@Composable
fun HourlyWeatherContent(weatherByHours: List<HourlyDataVo>) {
    AtmosCard { paddingValues ->
        Column(modifier = Modifier.padding(15.dp)) {
            AtmosText(text = "Tiempo por horas", type = TextType.Title)
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
