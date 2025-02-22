package com.devalr.dayweather.composables.weather.nowcomponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.dayweather.model.enums.WindDirectionText
import com.devalr.dayweather.model.now.WindState
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.TextType

@Composable
fun NowWeatherWindContent(
    windState: WindState,
    onWindPressed: () -> Unit
) {
    AtmosCard(onCardClicked = { onWindPressed.invoke() }) {
        Box(Modifier.fillMaxSize().padding(10.dp)) {
            if (windState.direction != WindDirectionText.None) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .align(Alignment.Center),
                    painter = painterResource(id = windState.direction.drawable),
                    contentDescription = windState.direction.name
                )
            }
            AtmosText(
                modifier = Modifier.align(Alignment.TopCenter),
                text = stringResource(R.string.now_weather_wind_label),
                type = TextType.Title
            )
            AtmosText(
                modifier = Modifier.align(Alignment.BottomCenter),
                text = "${windState.speed} km/h",
                type = TextType.Featured
            )
        }
    }
}


@Preview
@Composable
private fun NowWeatherWindContentPreview() {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowWeatherWindContent(
            windState = WindState(WindDirectionText.W, 3),
            onWindPressed = {}
        )
    }
}