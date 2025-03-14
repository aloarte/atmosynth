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
fun DetailHourlyContent(
    hourlyPrompt: PromptStateVo,
    onDismiss: () -> Unit,
) {
    AtmosBottomSheet(
        title = stringResource(R.string.hourly_detail_title),
        onDismiss = onDismiss
    ) {
        DetailHourlyContent(hourlyPrompt = hourlyPrompt)
    }
}

@Composable
private fun DetailHourlyContent(hourlyPrompt: PromptStateVo) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        if (hourlyPrompt.loadingAiPrompt) {
            AtmosAnimation(type = AnimationsType.LoadingAi, size = 120.dp)
        } else {
            if (hourlyPrompt.promptResult.isNullOrBlank()) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AtmosAnimation(type = AnimationsType.ActionError, size = 80.dp)
                    AtmosSeparator(size = 10.dp, type = SeparatorType.Vertical)
                    AtmosText(
                        text = stringResource(R.string.hourly_detail_error_description),
                        type = TextType.Description
                    )
                }
            } else {
                AtmosSeparator(size = 40.dp, type = SeparatorType.Vertical)
                AtmosText(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    text = hourlyPrompt.promptResult,
                    type = TextType.Description
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailHourlyContentPreviewLoadedSuccess() {
    AtmosynthTheme {
        DetailHourlyContent(
            hourlyPrompt = PromptStateVo(
                promptResult = stringResource(R.string.lorep_ipsum),
                loadingAiPrompt = false,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailHourlyContentContentPreviewLoadedFailedNull() {
    AtmosynthTheme {
        DetailHourlyContent(
            hourlyPrompt = PromptStateVo(
                promptResult = null,
                loadingAiPrompt = false,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailHourlyContentContentPreviewLoadedBlank() {
    AtmosynthTheme {
        DetailHourlyContent(
            hourlyPrompt = PromptStateVo(
                promptResult = "",
                loadingAiPrompt = false,
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DetailHourlyContentContentPreviewLoading() {
    AtmosynthTheme {
        DetailHourlyContent(
            hourlyPrompt = PromptStateVo(
                promptResult = stringResource(R.string.lorep_ipsum),
                loadingAiPrompt = true,
            )
        )
    }
}
