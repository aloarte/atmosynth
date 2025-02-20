package com.devalr.dayweather.composables.nowcomponents

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.TextType
import kotlin.math.sin

@Composable
fun NowWeatherHumidityContent(percentage: Float) {
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

    AtmosCard(halfScreen = true) {
        Box(Modifier.fillMaxSize()) {

            Canvas(modifier = Modifier.fillMaxSize()) {
                val width = size.width
                val height = size.height

                val waterHeight = (height * percentage) / 100

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

            AtmosText(
                modifier = Modifier
                    .padding(10.dp)
                    .align(
                        if (percentage > 78f) {
                            Alignment.BottomStart
                        } else {
                            Alignment.TopStart
                        }
                    ),
                text = "Humedad",
                type = TextType.Title
            )

            AtmosText(
                modifier = Modifier
                    .padding(20.dp)
                    .align(
                        if (percentage > 35f && percentage < 65f) {
                            Alignment.BottomCenter
                        } else {
                            Alignment.Center
                        }
                    ),
                text = "${percentage.toInt()}%",
                type = TextType.Featured
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreviewMin() {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowWeatherHumidityContent(percentage = 0f)
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreviewH63() {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowWeatherHumidityContent(percentage = 63f)
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview65() {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowWeatherHumidityContent(percentage = 65f)
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview36() {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowWeatherHumidityContent(percentage = 36f)
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview35() {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowWeatherHumidityContent(percentage = 35f)
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview80() {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowWeatherHumidityContent(percentage = 78f)
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreview81() {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowWeatherHumidityContent(percentage = 79f)
    }
}

@Preview(showBackground = true)
@Composable
fun NowWeatherHumidityContentPreviewMax() {
    Column(modifier = Modifier.fillMaxWidth()) {
        NowWeatherHumidityContent(percentage = 100f)
    }
}