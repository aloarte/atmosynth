package com.devalr.dayweather.composables.weather.nowcomponents

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme
import kotlin.math.sin

@Composable
fun NowWeatherHumidityContent(
    humidityPercentage: Float,
    onHumidityPressed: () -> Unit
) {
    AtmosCard(halfScreen = true, onCardClicked = { onHumidityPressed.invoke() }) {
        Box(Modifier.fillMaxSize()) {
            HumidityCanvas(humidityPercentage = humidityPercentage)
            AtmosText(
                modifier = Modifier
                    .padding(10.dp)
                    .align(getHumidityLabelPosition(humidityPercentage)),
                text = stringResource(R.string.now_weather_humidity_label),
                type = TextType.Title
            )
            AtmosText(
                modifier = Modifier
                    .padding(20.dp)
                    .align(getHumidityValuePosition(humidityPercentage)),
                text = "${humidityPercentage.toInt()}%",
                type = TextType.Featured
            )
        }
    }
}

@Composable
private fun HumidityCanvas(humidityPercentage: Float) {
    val infiniteTransition = rememberInfiniteTransition(label = "infinite")
    val waveOffset: Float by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 400f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 10000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        )
    )
    Canvas(modifier = Modifier.fillMaxSize()) {
        val width = size.width
        val height = size.height

        val waterHeight = (height * humidityPercentage) / 100

        val path = Path().apply {
            moveTo(0f, height - waterHeight)
            for (x in 0..width.toInt()) {
                val y =
                    (height - waterHeight) + sin((x + waveOffset) * 0.05f) * 10f
                lineTo(x.toFloat(), y)
            }
            lineTo(width, height)
            lineTo(0f, height)
            close()
        }

        drawPath(path, Color.Gray)
    }
}

private fun getHumidityLabelPosition(humidityPercentage: Float) = if (humidityPercentage > 78f) {
    Alignment.BottomCenter
} else {
    Alignment.TopCenter
}

private fun getHumidityValuePosition(humidityPercentage: Float) =
    if (humidityPercentage > 35f && humidityPercentage < 65f) {
        Alignment.BottomCenter
    } else {
        Alignment.Center
    }

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreviewMin() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherHumidityContent(humidityPercentage = 0f, onHumidityPressed = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreviewH63() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherHumidityContent(humidityPercentage = 63f, onHumidityPressed = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview65() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherHumidityContent(humidityPercentage = 65f, onHumidityPressed = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview36() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherHumidityContent(humidityPercentage = 36f, onHumidityPressed = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview35() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherHumidityContent(humidityPercentage = 35f, onHumidityPressed = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview80() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherHumidityContent(humidityPercentage = 78f, onHumidityPressed = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview81() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherHumidityContent(humidityPercentage = 79f, onHumidityPressed = {})
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreviewMax() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            NowWeatherHumidityContent(humidityPercentage = 100f, onHumidityPressed = {})
        }
    }
}