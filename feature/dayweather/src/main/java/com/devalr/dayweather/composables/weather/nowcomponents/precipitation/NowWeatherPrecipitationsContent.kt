package com.devalr.dayweather.composables.weather.nowcomponents.precipitation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun NowWeatherPrecipitationContent(
    rainPrecipitationProbability: Float,
    snowPrecipitationProbability: Float,
    onPrecipitationPressed: () -> Unit
) {
    AtmosCard(halfScreen = true, onCardClicked = { onPrecipitationPressed.invoke() }) {
        Box(Modifier.fillMaxSize()) {
            AtmosText(
                modifier = Modifier
                    .padding(10.dp)
                    .align(Alignment.TopCenter),
                text = stringResource(R.string.now_weather_precipitations_label),
                type = TextType.Title
            )
            if (snowPrecipitationProbability > rainPrecipitationProbability) {
                PrecipitationComponent(
                    modifier = Modifier.align(Alignment.Center),
                    precipitationProbability = snowPrecipitationProbability
                )

            } else {
                PrecipitationComponent(
                    modifier = Modifier.align(Alignment.Center),
                    precipitationProbability = rainPrecipitationProbability
                )
            }
        }
    }
}

@Composable
private fun PrecipitationComponent(
    modifier: Modifier = Modifier,
    precipitationProbability: Float
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AtmosSeparator(size = 20.dp, type = SeparatorType.Vertical)
        if (precipitationProbability > 0) {
            AtmosSeparator(size = 5.dp, type = SeparatorType.Vertical)
            AtmosAnimation(size = 60.dp, type = AnimationsType.WeatherRain)
            AtmosSeparator(size = 10.dp, type = SeparatorType.Vertical)

        }
        AtmosText(
            text = "${precipitationProbability}%",
            type = TextType.Featured
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherPrecipitationContentPreviewZero() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherPrecipitationContent(
                rainPrecipitationProbability = 0f,
                snowPrecipitationProbability = 0f,
                onPrecipitationPressed = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherPrecipitationContentPreviewRain() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherPrecipitationContent(
                rainPrecipitationProbability = 20f,
                snowPrecipitationProbability = 0f,
                onPrecipitationPressed = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherPrecipitationContentPreviewSnow() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherPrecipitationContent(
                rainPrecipitationProbability = 20f,
                snowPrecipitationProbability = 30f,
                onPrecipitationPressed = {})
        }
    }
}
