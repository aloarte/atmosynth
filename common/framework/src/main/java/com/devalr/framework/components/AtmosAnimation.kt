package com.devalr.framework.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.devalr.framework.R
import com.devalr.framework.enums.AnimationsType

@Composable
fun AtmosAnimation(type: AnimationsType, size: Dp = 40.dp) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(getAnimation(type)))
    LottieAnimation(
        modifier = Modifier.size(size),
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )
}

private fun getAnimation(type: AnimationsType): Int = when (type) {
    AnimationsType.ActionError -> R.raw.lottie_action_error
    AnimationsType.ActionWarning -> R.raw.lottie_action_warning
    AnimationsType.WeatherCold -> R.raw.lottie_weather_cold
    AnimationsType.WeatherClouds -> R.raw.lottie_weather_cloudy
    AnimationsType.WeatherClearDay -> R.raw.lottie_weather_day_clear
    AnimationsType.WeatherClearNight -> R.raw.lottie_weather_night_clear
    AnimationsType.WeatherWind -> R.raw.lottie_weather_wind
    AnimationsType.WeatherRain -> R.raw.lottie_weather_rain
    AnimationsType.LoadingAi -> R.raw.lottie_ai_loading
    AnimationsType.LoadingGeneral -> R.raw.lottie_loading_general
    AnimationsType.Humidity -> R.raw.lottie_humidity
    AnimationsType.Wind -> R.raw.lottie_wind
}

@Preview
@Composable
private fun AnimationsPreview() {
    Column {
        AnimationsType.entries.forEach {
            AtmosAnimation(it)

        }
    }

}