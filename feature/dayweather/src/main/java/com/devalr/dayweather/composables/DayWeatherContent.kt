package com.devalr.dayweather.composables

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.model.HourlyDataVo
import com.devalr.domain.model.enums.NowStatusType
import com.devalr.domain.model.now.NowStatusVo


@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DayWeatherContent(weatherByHours: List<HourlyDataVo>) {
    FlowRow(modifier = Modifier.padding(8.dp)) {
        NowWeatherContent(
            nowStatus = NowStatusVo(
                city = "Ciudad Real",
                temperature = "21º",
                thermalSensation = "20º",
                nowStatus = NowStatusType.Cloudy
            )
        )
        HourlyWeatherContent(weatherByHours)
    }

}


