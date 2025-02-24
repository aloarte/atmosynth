package com.devalr.dayweather.composables.weather.nowcomponents.wind

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.InfiniteTransition
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.dayweather.model.enums.WindDirectionText
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun NowWeatherWindArrow(
    modifier: Modifier = Modifier,
    windDirection: WindDirectionText,
) {
    val infiniteTransition = rememberInfiniteTransition()
    val (offsetX, offsetY) = getTranslationAxisPair(windDirection)
    val animatedOffsetX by axisAnimator(offsetX.value, infiniteTransition)
    val animatedOffsetY by axisAnimator(offsetY.value, infiniteTransition)


    Box(modifier = modifier) {
        if (windDirection != WindDirectionText.None) {
            Image(
                modifier = Modifier
                    .size(55.dp)
                    .align(Alignment.Center),
                painter = if (isSystemInDarkTheme()) {
                    painterResource(id = R.drawable.icon_circle_dark)
                } else {
                    painterResource(id = R.drawable.icon_circle_light)
                },
                contentDescription = windDirection.name
            )
        }
        Image(
            modifier = Modifier
                .offset(x = animatedOffsetX.dp, y = animatedOffsetY.dp)
                .size(40.dp)
                .align(Alignment.Center),
            painter = painterResource(id = windDirection.drawable),
            contentDescription = windDirection.name
        )
    }
}

private fun getTranslationAxisPair(windDirection: WindDirectionText) = when (windDirection) {
    WindDirectionText.N -> Pair(0.dp, 5.dp)
    WindDirectionText.NE -> Pair((-5).dp, 5.dp)
    WindDirectionText.E -> Pair((-5).dp, 0.dp)
    WindDirectionText.SE -> Pair((-5).dp, (-5).dp)
    WindDirectionText.S -> Pair((-0).dp, (-5).dp)
    WindDirectionText.SW -> Pair(5.dp, (-5).dp)
    WindDirectionText.W -> Pair(5.dp, 0.dp)
    WindDirectionText.NW -> Pair(5.dp, 5.dp)
    else -> Pair(0.dp, 0.dp)
}

@Composable
private fun axisAnimator(axisValue: Float, infiniteTransition: InfiniteTransition) =
    infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = axisValue,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutLinearInEasing
            ),
            repeatMode = RepeatMode.Reverse
        )
    )

@Preview(showBackground = true)
@Composable
private fun NowWeatherWindArrowPreview() {
    AtmosynthTheme {
        Column(modifier = Modifier.fillMaxWidth()) {
            WindDirectionText.entries.forEach {
                NowWeatherWindArrow(
                    modifier = Modifier.size(40.dp),
                    windDirection = it
                )
            }
        }
    }
}
