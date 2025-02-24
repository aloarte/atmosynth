package com.devalr.dayweather.composables.weather.nowcomponents.uv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.framework.components.AtmosSeparator
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.SeparatorType
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun UvLevel(highlighted: Boolean, label: String? = null) {
    Column(
        modifier = Modifier.height(60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Ball(getHighlightedOrGray(highlighted))
        AtmosSeparator(size = 10.dp, type = SeparatorType.Vertical)
        label?.let {
            AtmosText(
                textAlign = TextAlign.Center,
                text = label,
                type = TextType.LabelM
            )
        }
    }
}

@Composable
fun Ball(color: Color) {
    Box(
        modifier = Modifier
            .size(25.dp)
            .background(color, CircleShape)
    )
}

@Composable
private fun getHighlightedOrGray(highLighted: Boolean): Color = if (highLighted) {
    MaterialTheme.colorScheme.primary
} else {
    MaterialTheme.colorScheme.secondary
}

@Preview(showBackground = true)
@Composable
private fun UvLevelPreviewHighlighted() {
    AtmosynthTheme {
        UvLevel(true, "< 2")
    }
}

@Preview(showBackground = true)
@Composable
private fun UvLevelPreviewNotHighlighted() {
    AtmosynthTheme {
        UvLevel(false)
    }
}
