package com.devalr.dayweather.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.model.now.NowWeatherDataVo
import com.devalr.domain.model.enums.NowStatusType
import com.devalr.domain.model.now.NowStatusVo
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun NowWeatherContent(nowStatus: NowWeatherDataVo) {
    AtmosCard(width = 400.dp) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AtmosText(text = "Ciudad Real", type = TextType.Title)
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                //AtmosAnimation(size = 50.dp, type = getAnimation(nowStatus.nowStatus))
                AtmosSeparator(size = 5.dp, type = SeparatorType.Horizontal)
                AtmosText(text = nowStatus.temperature.current, type = TextType.UltraFeatured)
            }
            AtmosText(
                text = "Sensación térmica de ${nowStatus.thermalSensation.current}",
                type = TextType.LabelL
            )
            AtmosText(
                text = "Máxima ${nowStatus.temperature.max}  Mínima ${nowStatus.temperature.min}",
                type = TextType.LabelS
            )

        }
    }
}

private fun getAnimation(status: NowStatusType) = when (status) {
    NowStatusType.ClearDay -> AnimationsType.WeatherClearDay
    NowStatusType.ClearNight -> AnimationsType.WeatherClearNight
    NowStatusType.Cloudy -> AnimationsType.WeatherClouds
    NowStatusType.Cold -> AnimationsType.WeatherCold
    NowStatusType.Rain -> AnimationsType.WeatherRain
    NowStatusType.Windy -> AnimationsType.WeatherWind
}
/*
@Preview(showBackground = true)
@Composable
private fun NowWeatherContentPreviewCold() {
    AtmosynthTheme {
        NowWeatherContent(
            nowStatus = NowStatusVo(
                city = "London",
                temperature = "0º",
                thermalSensation = "-1º",
                nowStatus = NowStatusType.Cold
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherContentPreviewClearDay() {
    AtmosynthTheme {
        NowWeatherContent(
            nowStatus = NowStatusVo(
                city = "London",
                temperature = "20º",
                thermalSensation = "20º",
                nowStatus = NowStatusType.ClearDay
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherContentPreviewClearNight() {
    AtmosynthTheme {
        NowWeatherContent(
            nowStatus = NowStatusVo(
                city = "London",
                temperature = "18º",
                thermalSensation = "16º",
                nowStatus = NowStatusType.ClearNight
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherContentPreviewRain() {
    AtmosynthTheme {
        NowWeatherContent(
            nowStatus = NowStatusVo(
                city = "London",
                temperature = "18º",
                thermalSensation = "16º",
                nowStatus = NowStatusType.Rain
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherContentPreviewWindy() {
    AtmosynthTheme {
        NowWeatherContent(
            nowStatus = NowStatusVo(
                city = "London",
                temperature = "10º",
                thermalSensation = "8º",
                nowStatus = NowStatusType.Windy
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherContentPreviewCloudy() {
    AtmosynthTheme {
        NowWeatherContent(
            nowStatus = NowStatusVo(
                city = "London",
                temperature = "10º",
                thermalSensation = "8º",
                nowStatus = NowStatusType.Cloudy
            )
        )
    }
}*/






