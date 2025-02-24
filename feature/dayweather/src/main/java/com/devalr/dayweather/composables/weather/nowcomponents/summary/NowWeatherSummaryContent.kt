package com.devalr.dayweather.composables.weather.nowcomponents.summary

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosButton
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun DailySummaryContent(loadingAiPrompt: Boolean, promptResult: String?, onRetry: () -> Unit) {
    AtmosCard(height = 280.dp, isClickable = false) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (loadingAiPrompt) {
                AtmosAnimation(type = AnimationsType.LoadingAi, size = 120.dp)
            } else {
                AtmosText(
                    text = stringResource(R.string.now_weather_summary_title),
                    type = TextType.Title
                )
                AtmosSeparator(size = 5.dp, type = SeparatorType.Vertical)
                if (promptResult.isNullOrBlank()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AtmosText(
                            text = stringResource(R.string.now_weather_summary_error_description),
                            type = TextType.Description
                        )
                        AtmosSeparator(size = 30.dp, type = SeparatorType.Vertical)
                        AtmosButton(
                            text = stringResource(R.string.now_weather_summary_error_action),
                            onClick = onRetry
                        )
                    }
                } else {
                    AtmosText(text = promptResult, type = TextType.Description)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DailySummaryContentPreviewLoadedSuccess() {
    AtmosynthTheme {
        DailySummaryContent(
            promptResult = stringResource(R.string.lorep_ipsum),
            loadingAiPrompt = false,
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DailySummaryContentPreviewLoadedFailedNull() {
    AtmosynthTheme {
        DailySummaryContent(
            promptResult = null,
            loadingAiPrompt = false,
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DailySummaryContentPreviewLoadedBlank() {
    AtmosynthTheme {
        DailySummaryContent(
            promptResult = "",
            loadingAiPrompt = false,
            onRetry = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DailySummaryContentPreviewLoading() {
    AtmosynthTheme {
        DailySummaryContent(
            promptResult = stringResource(R.string.lorep_ipsum),
            loadingAiPrompt = true,
            onRetry = {}
        )
    }
}