package com.devalr.cityselector.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.devalr.domain.model.CityBo
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.TextType
import com.devalr.framework.theme.AtmosynthTheme

@Composable
fun City(city: CityBo, cityClicked: (CityBo) -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(15.dp))
            .background(Color.Gray)
            .fillMaxWidth()
            .clickable {
                cityClicked(city)
            }
            .padding(20.dp)
    ) {
        AtmosText(
            modifier = Modifier.align(Alignment.CenterStart),
            text = city.name,
            type = TextType.Title
        )
        AtmosText(
            modifier = Modifier.align(Alignment.CenterEnd),
            text = city.id,
            type = TextType.Title
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun CityPreview() {
    AtmosynthTheme {
        City(
            city = CityBo(
                name = "Madrid",
                population = "10000",
                id = "id10000"
            ),
            cityClicked = {}
        )
    }
}
