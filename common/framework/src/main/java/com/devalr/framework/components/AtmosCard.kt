package com.devalr.framework.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
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
fun AtmosCard(
    width: Dp? = null,
    height: Dp? = null,
    halfScreen: Boolean = false,
    onCardClicked: () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(5.dp)
            .then(
                Modifier.run {
                    if (halfScreen) {
                        fillMaxWidth(0.5f)
                    } else {
                        width?.let {
                            width(it)
                        } ?: fillMaxWidth()
                    }
                }
            )
            .then(
                Modifier.run {
                    height?.let {
                        height(it)
                    } ?: height(150.dp)
                }
            )
            .clickable {
                onCardClicked.invoke()
            }
    ) {
        content(PaddingValues(5.dp))
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

@Preview(showBackground = true)
@Composable
fun AtmosCardPreviewCustomHeight() {
    AtmosynthTheme {
        AtmosCard(height = 300.dp) {
            AtmosText(text = "Card content", type = TextType.Title)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AtmosCardPreviewCustomHeightWidth() {
    AtmosynthTheme {
        AtmosCard(width = 200.dp, height = 80.dp) {
            AtmosText(text = "Card content", type = TextType.Title)
        }
    }
}