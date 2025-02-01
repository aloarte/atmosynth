package com.devalr.dayweather

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import com.devalr.framework.CustomText
import org.koin.compose.koinInject

@Composable
fun DayWeatherScreen(viewModel: DayWeatherViewModel = koinInject()) {
    val state by viewModel.state.collectAsState()
    Column {
        CustomText(text = "prompt result:")
        CustomText(text = state.promptResult)
    }
}

@Preview(showSystemUi = true)
@Composable
fun BakingScreenPreview() {
    DayWeatherScreen()
}
