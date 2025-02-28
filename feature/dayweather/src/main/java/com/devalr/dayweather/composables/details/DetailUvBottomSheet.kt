package com.devalr.dayweather.composables.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.dayweather.model.PromptStateVo
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.modals.AtmosBottomSheet
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun DetailUvBottomSheet(
    uv: String,
    uvPrompt: PromptStateVo,
    onDismiss: () -> Unit,
) {
    AtmosBottomSheet(
        title = stringResource(R.string.uv_detail_title),
        onDismiss = onDismiss
    ) {
        DetailUvContent(
            uv = uv,
            uvPrompt = uvPrompt
        )
    }
}

@Composable
private fun DetailUvContent(
    uv: String,
    uvPrompt: PromptStateVo
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (uvPrompt.loadingAiPrompt) {
            AtmosAnimation(type = AnimationsType.LoadingAi, size = 120.dp)
        } else {
            if (uvPrompt.promptResult.isNullOrBlank()) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AtmosAnimation(type = AnimationsType.ActionError, size = 100.dp)
                    AtmosSeparator(size = 20.dp, type = SeparatorType.Vertical)
                    AtmosText(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.uv_detail_error_description),
                        type = TextType.Description
                    )
                }
            } else {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    AtmosAnimation(type = AnimationsType.WeatherClearDay, size = 60.dp)
                    AtmosText(
                        text = "Nivel $uv",
                        type = TextType.UltraFeatured
                    )
                }
                AtmosSeparator(size = 40.dp, type = SeparatorType.Vertical)
                AtmosText(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = uvPrompt.promptResult,
                    type = TextType.Description
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailUvContentPreviewSuccessN() {
    AtmosynthTheme {
        Column {
            DetailUvContent(
                uv = "4",
                uvPrompt = PromptStateVo(
                    promptResult = stringResource(R.string.lorep_ipsum),
                    loadingAiPrompt = false,
                )
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailUvContentPreviewLoadedFailedNull() {
    AtmosynthTheme {
        DetailUvContent(
            uv = "4",
            uvPrompt = PromptStateVo(
                promptResult = null,
                loadingAiPrompt = false,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailUvContentPreviewLoadedBlank() {
    AtmosynthTheme {
        DetailUvContent(
            uv = "4",
            uvPrompt = PromptStateVo(
                promptResult = "",
                loadingAiPrompt = false,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DDetailUvContentPreviewLoading() {
    AtmosynthTheme {
        DetailUvContent(
            uv = "4",
            uvPrompt = PromptStateVo(
                promptResult = stringResource(R.string.lorep_ipsum),
                loadingAiPrompt = true,
            )
        )
    }
}