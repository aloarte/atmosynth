package com.devalr.framework.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosButton
import com.devalr.framework.enums.AnimationsType

@Composable
fun ErrorScreen(errorMessage: String, buttonMessage: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        Text(text = errorMessage)
        AtmosAnimation(type = AnimationsType.ActionError)
        AtmosButton(text = buttonMessage, onClick = onClick)
    }
}