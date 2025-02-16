package com.devalr.framework.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun AtmosSeparator(size: Dp, type: SeparatorType) {
    when (type) {
        SeparatorType.Horizontal -> HorizontalDivider(
            modifier = Modifier.width(size),
            color = Color.Transparent
        )

        SeparatorType.Vertical -> VerticalDivider(
            modifier = Modifier.height(size),
            color = Color.Transparent
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun AtmosSeparatorPreview() {
    AtmosynthTheme {
        Column {
            Column {
                AtmosText(text = "Text1", type = TextType.Title)
                AtmosSeparator(size = 20.dp, type = SeparatorType.Vertical)
                AtmosText(text = "Text2", type = TextType.Title)
            }
            Row {
                AtmosText(text = "Text1", type = TextType.Title)
                AtmosSeparator(size = 20.dp, type = SeparatorType.Horizontal)
                AtmosText(text = "Text2", type = TextType.Title)
            }
        }
    }
}