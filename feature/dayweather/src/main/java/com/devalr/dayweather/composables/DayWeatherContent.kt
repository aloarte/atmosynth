package com.devalr.dayweather.composables

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.composables.hourlycomponents.HourlyWeatherContent
import com.devalr.dayweather.composables.nowcomponents.DailySummaryContent
import com.devalr.dayweather.composables.nowcomponents.NowWeatherContent
import com.devalr.dayweather.composables.nowcomponents.NowWeatherHumidityContent
import com.devalr.dayweather.model.hourly.HourlyDataVo
import com.devalr.dayweather.model.now.NowWeatherDataVo

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DayWeatherContent(
    dailyWeather: NowWeatherDataVo?,
    hourlyWeather: List<HourlyDataVo>,
    promptResult:String?,
    loadingAiPrompt:Boolean
) {
    FlowRow(modifier = Modifier.padding(8.dp)) {
        dailyWeather?.let {
            NowWeatherHumidityContent(dailyWeather.humidity.current.toFloat())
            NowWeatherContent(nowStatus = dailyWeather)
        }
        HourlyWeatherContent(hourlyWeather)
        promptResult?.let{
            DailySummaryContent(promptResult,loadingAiPrompt)
        }

    }

}


