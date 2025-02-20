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
import com.devalr.framework.components.AtmosCard
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.TextType

@Composable
fun DailySummaryContent(promptResult: String, loadingAiPrompt: Boolean) {
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
                AtmosText(text = promptResult, type = TextType.Description)
            }
        }
    }
}

@Preview
@Composable
private fun DailySummaryContentPreviewLoaded() {
    DailySummaryContent(
        promptResult = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        loadingAiPrompt = false
    )
}


@Preview
@Composable
private fun DailySummaryContentPreviewLoading() {
    DailySummaryContent(
        promptResult = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
        loadingAiPrompt = true
    )
}