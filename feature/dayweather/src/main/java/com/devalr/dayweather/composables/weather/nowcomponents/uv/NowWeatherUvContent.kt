package com.devalr.dayweather.composables.weather.nowcomponents.uv

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
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun NowWeatherUvContent(
    uvValue: String,
    onUvPressed: () -> Unit
) {
    AtmosCard(
        onCardClicked = { onUvPressed.invoke() }) {
        Box(
            Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            AtmosText(
                modifier = Modifier.align(Alignment.TopCenter),
                text = stringResource(R.string.now_weather_uv_label),
                type = TextType.Title
            )
            Column(
                modifier = Modifier.align(Alignment.Center),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AtmosSeparator(30.dp, SeparatorType.Vertical)
                AtmosText(
                    text = uvValue,
                    type = TextType.Featured
                )
                AtmosSeparator(10.dp, SeparatorType.Vertical)
                UvIndexSemaphore(
                    uvIndex = uvValue.toInt()
                )
                AtmosSeparator(10.dp, SeparatorType.Vertical)
                AtmosText(
                    text = getUvText(uvValue.toInt()),
                    type = TextType.Title
                )
            }
        }
    }
}

private fun getUvText(uvIndex: Int): String {
    return when (uvIndex) {
        in 0..2 -> "Bajo"
        in 3..5 -> "Moderado"
        in 6..7 -> "Alto"
        in 8..10 -> "Muy alto"
        else -> "Extremo"
    }
}

@Preview
@Composable
private fun NowWeatherWindContentPreviewFirst() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherUvContent(uvValue = "1", onUvPressed = {})
        }
    }
}
