package com.devalr.dayweather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.composables.HourlyEventItem
import com.devalr.dayweather.composables.HourlyWeatherItem
import com.devalr.dayweather.model.HourlyEventVo
import com.devalr.dayweather.model.HourlyWeatherVo
import com.devalr.framework.CustomText
import org.koin.compose.koinInject

@Composable
fun DayWeatherScreen(viewModel: DayWeatherViewModel = koinInject()) {
    val state by viewModel.state.collectAsState()
    Column {
        Column {
            CustomText(text = "prompt result:")
            CustomText(text = state.promptResult)
        }

        LazyRow {
            items(state.weatherByHours) {
                when (it) {
                    is HourlyWeatherVo -> HourlyWeatherItem(it)
                    is HourlyEventVo -> HourlyEventItem(it)
                }
            }
        }
    }
}




@Preview(showSystemUi = true)
@Composable
fun BakingScreenPreview() {
    DayWeatherScreen()
}
