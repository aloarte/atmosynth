package com.devalr.framework.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun AtmosCard(width: Dp? = null, content: @Composable () -> Unit) {
    val modifier = Modifier
        .padding(5.dp)
        .height(100.dp)
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = width?.let {
            modifier.width(it)
        } ?: modifier.fillMaxWidth()
    ) {
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun AtmosCardPreviewFillMaxWidth() {
    AtmosynthTheme {
        AtmosCard {
            AtmosText(text = "Card content", type = TextType.Title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AtmosCardPreviewCustomWidth() {
    AtmosynthTheme {
        AtmosCard(width = 300.dp) {
            AtmosText(text = "Card content", type = TextType.Title)
        }
    }
}