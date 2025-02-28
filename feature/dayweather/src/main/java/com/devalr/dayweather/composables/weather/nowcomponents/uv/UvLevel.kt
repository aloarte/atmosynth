package com.devalr.dayweather.composables.weather.nowcomponents.uv

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun Ball(highlighted: Boolean) {
    Box(
        modifier = Modifier
            .size(if(highlighted){25.dp} else {15.dp})
            .background(getHighlightedOrGray(highlighted), CircleShape)
    )
}

@Composable
private fun getHighlightedOrGray(highlighted: Boolean): Color = if (highlighted) {
    MaterialTheme.colorScheme.primary
} else {
    MaterialTheme.colorScheme.secondary
}

@Preview(showBackground = true)
@Composable
private fun UvLevelPreviewHighlighted() {
    AtmosynthTheme {
        Ball(true)
    }
}

@Preview(showBackground = true)
@Composable
private fun UvLevelPreviewNotHighlighted() {
    AtmosynthTheme {
        Ball(false)
    }
}
