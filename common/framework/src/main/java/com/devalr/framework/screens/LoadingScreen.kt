package com.devalr.framework.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.framework.components.AtmosAnimation
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.AnimationsType
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun LoadingScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(60.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AtmosAnimation(type = AnimationsType.LoadingGeneral, size = 120.dp)
        VerticalDivider(modifier = Modifier.height(50.dp), color = Color.Transparent)
        AtmosText(
            text = "Estamos cargando los datos. Por favor, espera unos segundos.",
            type = TextType.Description
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun LoadingScreenPreview() {
    AtmosynthTheme {
        LoadingScreen()
    }
}