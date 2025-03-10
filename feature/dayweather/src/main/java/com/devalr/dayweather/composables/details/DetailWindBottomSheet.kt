package com.devalr.dayweather.composables.details

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.dayweather.R
import com.devalr.dayweather.model.PromptStateVo
import com.devalr.dayweather.model.enums.WindDirectionText
import com.devalr.dayweather.model.now.WindState
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.modals.AtmosBottomSheet
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun DetailWindBottomSheet(
    wind: WindState,
    windPrompt: PromptStateVo,
    onDismiss: () -> Unit,
) {
    AtmosBottomSheet(
        title = stringResource(R.string.wind_detail_title),
        onDismiss = onDismiss
    ) {
        DetailWindContent(
            wind = wind,
            windPrompt = windPrompt
        )
    }
}

@Composable
private fun DetailWindContent(
    wind: WindState,
    windPrompt: PromptStateVo
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (windPrompt.loadingAiPrompt) {
            AtmosAnimation(type = AnimationsType.LoadingAi, size = 120.dp)
        } else {
            if (windPrompt.promptResult.isNullOrBlank()) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AtmosAnimation(type = AnimationsType.ActionError, size = 100.dp)
                    AtmosSeparator(size = 20.dp, type = SeparatorType.Vertical)
                    AtmosText(
                        textAlign = TextAlign.Center,
                        text = stringResource(R.string.wind_detail_error_description),
                        type = TextType.Description
                    )
                }
            } else {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    AtmosAnimation(type = AnimationsType.Wind, size = 60.dp)
                    AtmosText(
                        text = "${wind.speed} km/h",
                        type = TextType.UltraFeatured
                    )
                }
                AtmosSeparator(size = 40.dp, type = SeparatorType.Vertical)
                AtmosText(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = windPrompt.promptResult,
                    type = TextType.Description
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailWindContentPreviewLoadedSuccessN() {
    AtmosynthTheme {
        Column {
            WindDirectionText.entries.forEach {
                DetailWindContent(
                    wind = WindState(it, 20),
                    windPrompt = PromptStateVo(
                        promptResult = stringResource(R.string.lorep_ipsum),
                        loadingAiPrompt = false,
                    )
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailWindContentPreviewLoadedFailedNull() {
    AtmosynthTheme {
        DetailWindContent(
            wind = WindState(WindDirectionText.N, 20),
            windPrompt = PromptStateVo(
                promptResult = null,
                loadingAiPrompt = false,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailWindContentPreviewLoadedBlank() {
    AtmosynthTheme {
        DetailWindContent(
            wind = WindState(WindDirectionText.N, 20),
            windPrompt = PromptStateVo(
                promptResult = "",
                loadingAiPrompt = false,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailWindContentPreviewLoading() {
    AtmosynthTheme {
        DetailWindContent(
            wind = WindState(WindDirectionText.NE, 20),
            windPrompt = PromptStateVo(
                promptResult = stringResource(R.string.lorep_ipsum),
                loadingAiPrompt = true,
            )
        )
    }
}