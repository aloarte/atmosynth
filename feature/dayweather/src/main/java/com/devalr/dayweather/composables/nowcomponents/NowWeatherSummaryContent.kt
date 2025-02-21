package com.devalr.dayweather.composables.nowcomponents

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    AtmosCard(height = 250.dp) { paddingValues ->
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
                AtmosText(text = "Resumen de la previsión de hoy:", type = TextType.Title)
                AtmosSeparator(size = 5.dp, type = SeparatorType.Vertical)
                if (promptResult.isNullOrBlank()) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AtmosText(
                            text = "Algo salió mal y no se pudo generar el resumen de la previsión diaria. Por favor, pulsa en reintentar.",
                            type = TextType.Description
                        )
                        AtmosSeparator(size = 30.dp, type = SeparatorType.Vertical)
                        AtmosButton(text = "Reintentar", onClick = onRetry)
                    }
                } else {
                    AtmosText(text = promptResult, type = TextType.Description)
                }
            }
        }
    }
}

@Preview
@Composable
private fun DailySummaryContentPreviewLoadedSuccess() {
    AtmosynthTheme {
        DailySummaryContent(
            promptResult = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            loadingAiPrompt = false,
            onRetry = {}
        )
    }
}

@Preview
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

@Preview
@Composable
private fun DailySummaryContentPreviewLoadedBlank() {
    AtmosynthTheme {
        DailySummaryContent(
            promptResult = null,
            loadingAiPrompt = false,
            onRetry = {}
        )
    }
}

@Preview
@Composable
private fun DailySummaryContentPreviewLoading() {
    AtmosynthTheme {
        DailySummaryContent(
            promptResult = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
            loadingAiPrompt = true,
            onRetry = {}
        )
    }
}