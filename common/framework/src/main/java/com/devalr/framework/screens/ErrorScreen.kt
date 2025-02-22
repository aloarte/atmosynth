package com.devalr.framework.screens

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
import com.devalr.framework.R
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosButton
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun ErrorScreen(errorMessage: String, buttonMessage: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AtmosAnimation(type = AnimationsType.ActionError, size = 120.dp)
        AtmosSeparator(size = 5.dp, type = SeparatorType.Vertical)
        AtmosText(
            text = stringResource(R.string.error_screen_title),
            type = TextType.Title
        )
        AtmosText(
            text = errorMessage,
            type = TextType.Description
        )
        AtmosSeparator(size = 20.dp, type = SeparatorType.Vertical)
        AtmosButton(text = buttonMessage, onClick = onClick)

    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorScreenPreview() {
    AtmosynthTheme {
        ErrorScreen(
            errorMessage = "Algo salió mal. Por favor, vuelve a intentarlo.",
            buttonMessage = "Retry",
            onClick = {})
    }

}