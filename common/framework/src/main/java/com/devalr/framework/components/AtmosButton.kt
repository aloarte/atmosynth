package com.devalr.framework.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun AtmosButton(modifier: Modifier = Modifier, text: String, onClick: () -> Unit) {
    Button(
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        ),
        onClick = onClick
    ) {
        AtmosText(text = text.uppercase(), type = TextType.Title)
    }
}


@Preview
@Composable
private fun AtmosButtonPreviews() {
    AtmosynthTheme {
        Column {
            AtmosButton(text = "Retry", onClick = {})
            VerticalDivider(modifier = Modifier.height(20.dp))
            AtmosButton(text = "Retry again with logout", onClick = {})
        }
    }
}