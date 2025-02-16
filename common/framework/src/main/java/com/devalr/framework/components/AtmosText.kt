package com.devalr.framework.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.devalr.framework.enums.TextType

@Composable
fun AtmosText(modifier: Modifier = Modifier, text: String, textColor: Color = Color.Unspecified, type: TextType) {
    Text(
        modifier = modifier,
        text = text,
        color = textColor,
        fontWeight = getTextWeight(type),
        fontSize = getTextSize(type)
    )
}

private fun getTextSize(type: TextType) = when (type) {
    TextType.UltraFeatured -> 40.sp
    TextType.Title -> 14.sp
    TextType.Description -> 12.sp
    TextType.LabelXs -> 8.sp
    TextType.LabelS -> 10.sp
    TextType.LabelM -> 12.sp
    TextType.LabelL -> 14.sp
}

private fun getTextWeight(type: TextType) = when (type) {
    TextType.UltraFeatured ->  FontWeight.ExtraBold
    TextType.Title -> FontWeight.Bold
    TextType.Description -> FontWeight.Normal
    TextType.LabelXs -> FontWeight.Thin
    TextType.LabelS -> FontWeight.Thin
    TextType.LabelM -> FontWeight.Thin
    TextType.LabelL -> FontWeight.Thin
}