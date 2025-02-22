package com.devalr.dayweather.composables.detailhumidity

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.dayweather.model.PromptStateVo
import com.devalr.dayweather.model.now.WeatherMaxMin
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosButton
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.modals.AtmosBottomSheet
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun DetailHumidityBottomSheet(
    humidity: WeatherMaxMin,
    humidityPrompt: PromptStateVo,
    onDismiss: () -> Unit,
) {
    AtmosBottomSheet(
        title = stringResource(R.string.humidity_detail_title),
        onDismiss = onDismiss
    ) {
        DetailHumidityContent(
            humidity = humidity,
            humidityPrompt = humidityPrompt,
            onDismiss = onDismiss
        )
    }
}

@Composable
private fun DetailHumidityContent(
    humidity: WeatherMaxMin,
    humidityPrompt: PromptStateVo,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (humidityPrompt.loadingAiPrompt) {
            AtmosAnimation(type = AnimationsType.LoadingAi, size = 120.dp)
        } else {
            if (humidityPrompt.promptResult.isNullOrBlank()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AtmosText(
                        text = stringResource(R.string.humidity_detail_error_description),
                        type = TextType.Description
                    )
                    AtmosSeparator(size = 30.dp, type = SeparatorType.Vertical)
                    AtmosButton(
                        text = stringResource(R.string.humidity_detail_error_action),
                        onClick = onDismiss
                    )
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AtmosAnimation(type = AnimationsType.Humidity, size = 60.dp)
                    AtmosText(
                        text = "${humidity.current}%",
                        type = TextType.UltraFeatured
                    )
                }
                AtmosSeparator(size = 40.dp, type = SeparatorType.Vertical)
                AtmosText(text = humidityPrompt.promptResult, type = TextType.Description)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailHumidityContentPreviewLoadedSuccess() {
    AtmosynthTheme {
        DetailHumidityContent(
            humidity = WeatherMaxMin("50", "60", "20"),
            humidityPrompt = PromptStateVo(
                promptResult = stringResource(R.string.lorep_ipsum),
                loadingAiPrompt = false,
            ),
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailHumidityContentPreviewLoadedFailedNull() {
    AtmosynthTheme {
        DetailHumidityContent(
            humidity = WeatherMaxMin("50", "60", "20"),
            humidityPrompt = PromptStateVo(
                promptResult = null,
                loadingAiPrompt = false,
            ),
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailHumidityContentPreviewLoadedBlank() {
    AtmosynthTheme {
        DetailHumidityContent(
            humidity = WeatherMaxMin("50", "60", "20"),
            humidityPrompt = PromptStateVo(
                promptResult = "",
                loadingAiPrompt = false,
            ),
            onDismiss = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailHumidityContentPreviewLoading() {
    AtmosynthTheme {
        DetailHumidityContent(
            humidity = WeatherMaxMin("50", "60", "20"),
            humidityPrompt = PromptStateVo(
                promptResult = stringResource(R.string.lorep_ipsum),
                loadingAiPrompt = true,
            ),
            onDismiss = {}
        )
    }
}

