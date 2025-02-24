package com.devalr.dayweather.composables.weather.nowcomponents.uv

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun UvIndexSemaphore(uvIndex: Int) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(3.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        UvLevel(uvIndex <= 2, "< 2")
        UvLevel(uvIndex in 3..5)
        UvLevel(uvIndex in 6..7)
        UvLevel(uvIndex in 8..10)
        UvLevel(uvIndex >= 11, "> 11")
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherWindContentPreviewFirst() {
    AtmosynthTheme {
        UvIndexSemaphore(uvIndex = 1)
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherWindContentPreviewSecond() {
    AtmosynthTheme {
        UvIndexSemaphore(uvIndex = 3)
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherWindContentPreviewThird() {
    AtmosynthTheme {
        UvIndexSemaphore(uvIndex = 6)
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherWindContentPreviewForth() {
    AtmosynthTheme {
        UvIndexSemaphore(uvIndex = 8)
    }
}

@Preview(showBackground = true)
@Composable
private fun NowWeatherWindContentPreviewFifth() {
    AtmosynthTheme {
        UvIndexSemaphore(uvIndex = 11)
    }
}