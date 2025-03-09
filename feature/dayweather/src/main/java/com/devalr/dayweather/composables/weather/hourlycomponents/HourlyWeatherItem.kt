package com.devalr.dayweather.composables.weather.hourlycomponents

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.devalr.dayweather.R
import com.devalr.dayweather.model.enums.SkyStateIcon
import com.devalr.dayweather.model.hourly.HourlyWeatherVo
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme
import java.time.LocalDateTime

@Composable
fun HourlyWeatherItem(data: HourlyWeatherVo) {
    AtmosCard(
        isClickable = false,
        backgroundColor = MaterialTheme.colorScheme.tertiaryContainer
    ) { paddingValues ->
        ConstraintLayout(
            modifier = Modifier
                .size(width = 40.dp, height = 100.dp)
                .padding(paddingValues)
        ) {
            val (temperature, icon, precipitation, hour) = createRefs()
            AtmosText(
                modifier = Modifier.constrainAs(temperature) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
                text = data.temperature,
                type = TextType.LabelS
            )
            WeatherImage(
                modifier = Modifier.constrainAs(icon) {
                    top.linkTo(temperature.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                skyStateIcon = data.skyState
            )
            if ((data.precipitationProbability == "0%").not()) {
                AtmosText(
                    modifier = Modifier
                        .constrainAs(precipitation) {
                            top.linkTo(icon.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    textColor = Color.Blue,
                    text = data.precipitationProbability,
                    type = TextType.LabelXs
                )

            }
            AtmosText(
                modifier = Modifier.constrainAs(hour) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = data.hour,
                type = TextType.LabelS
            )
        }
    }
}

@Composable
private fun WeatherImage(modifier: Modifier = Modifier, skyStateIcon: SkyStateIcon) {
    AsyncImage(
        modifier = modifier,
        model = "https://openweathermap.org/img/wn/${skyStateIcon.pngValue}@2x.png",
        contentDescription = null,
        placeholder = painterResource(R.drawable.weather_loading),
    )
}

@Preview
@Composable
private fun HourlyWeatherItemWithPrecipitationPreview() {
    AtmosynthTheme {
        HourlyWeatherItem(
            HourlyWeatherVo(
                hour = "19:00",
                humidity = "60",
                skyState = SkyStateIcon.DayClear,
                temperature = "12",
                precipitationProbability = "3%",
                thermalSensation = "12",
                completeTime = LocalDateTime.now()
            )
        )
    }
}

@Preview
@Composable
private fun HourlyWeatherItemWithoutPrecipitationPreview() {
    AtmosynthTheme {
        HourlyWeatherItem(
            HourlyWeatherVo(
                hour = "19:00",
                humidity = "60",
                skyState = SkyStateIcon.DayClear,
                temperature = "12",
                precipitationProbability = "",
                thermalSensation = "12",
                completeTime = LocalDateTime.now()
            )
        )
    }
}