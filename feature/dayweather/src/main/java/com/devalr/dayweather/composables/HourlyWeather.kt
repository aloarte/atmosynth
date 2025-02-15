package com.devalr.dayweather.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil3.compose.AsyncImage
import com.devalr.dayweather.R
import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.dayweather.model.SkyStateIcon
import java.time.LocalDateTime

@Composable
fun HourlyWeatherItem(data: HourlyWeatherVo) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(2.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .size(width = 40.dp, height = 100.dp)
                .padding(5.dp)
        ) {
            val (temperature, icon, precipitation, hour) = createRefs()

            Text(
                modifier = Modifier.constrainAs(temperature) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
                text = data.temperature,
                fontSize = 10.sp
            )
            WeatherImage(
                modifier = Modifier.constrainAs(icon) {
                    top.linkTo(temperature.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                skyStateIcon = data.skyState
            )
            if (data.precipitationProbability.isNotBlank()) {
                Text(
                    modifier = Modifier
                        .constrainAs(precipitation) {
                            top.linkTo(icon.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    text = data.precipitationProbability,
                    fontSize = 6.sp
                )
            }
            Text(
                modifier = Modifier.constrainAs(hour) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = data.hour,
                fontSize = 10.sp
            )
        }
    }

}

@Composable
fun WeatherImage(modifier: Modifier = Modifier, skyStateIcon: SkyStateIcon) {
    AsyncImage(
        modifier = modifier,
        model = "https://openweathermap.org/img/wn/${skyStateIcon.pngValue}@2x.png",
        contentDescription = null,
        placeholder = painterResource(R.drawable.error_weather),
    )
}

@Preview
@Composable
fun HourlyWeatherItemPreview() {
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