package com.devalr.dayweather.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.devalr.dayweather.model.HourlyEventVo
import com.devalr.dayweather.model.enums.HourlyEvent
import com.devalr.framework.components.AtmosText
import com.devalr.framework.enums.TextType
import java.time.LocalDateTime

@Composable
fun HourlyEventItem(event: HourlyEventVo) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        modifier = Modifier
            .padding(2.dp)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .size(width = 60.dp, height = 100.dp)
                .padding(5.dp)
        ) {
            val (name, icon, hour) = createRefs()

            AtmosText(
                modifier = Modifier.constrainAs(name) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                },
                text = event.event.value,
                type = TextType.LabelXs
            )
           
            HourlyEventImage(
                modifier = Modifier.constrainAs(icon) {
                    top.linkTo(name.bottom, margin = 5.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                event = event.event
            )

            AtmosText(
                modifier = Modifier.constrainAs(hour) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                },
                text = event.hour,
                type = TextType.LabelS
            )
        }
    }
}

@Composable
private fun HourlyEventImage(modifier: Modifier = Modifier, event: HourlyEvent) {
    Image(
        modifier = modifier.size(18.dp),
        contentDescription = null,
        painter = painterResource(event.iconResource),
    )
}

@Preview
@Composable
private fun HourlyEventItemSunsetPreview() {
    HourlyEventItem(
        HourlyEventVo(
            hour = "18:05",
            completeTime = LocalDateTime.now(),
            event = HourlyEvent.Sunset
        )
    )
}

@Preview
@Composable
private fun HourlyEventItemSunrisePreview() {
    HourlyEventItem(
        HourlyEventVo(
            hour = "08:17",
            completeTime = LocalDateTime.now(),
            event = HourlyEvent.Sunrise
        )
    )
}