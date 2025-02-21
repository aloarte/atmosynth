package com.devalr.dayweather.composables.detailhumidity

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.devalr.dayweather.interactions.State
import com.devalr.dayweather.model.enums.HourlyEvent
import com.devalr.dayweather.model.hourly.HourlyEventVo
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.framework.components.AtmosButton
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.theme.AtmosynthTheme
import java.time.LocalDateTime

@Composable
fun   DetailHumidityContent(humidityPercentage: Float, onBack: () -> Unit) {
    Column {
        AtmosButton(text = "back", onClick = onBack)
    }

}

