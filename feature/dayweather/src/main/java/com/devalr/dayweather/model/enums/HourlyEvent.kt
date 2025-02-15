package com.devalr.dayweather.model.enums

import androidx.annotation.DrawableRes
import com.devalr.dayweather.R

enum class HourlyEvent(
    @DrawableRes val iconResource: Int,
    val value: String
) {
    Sunrise(R.drawable.icon_sunrise, "Amanecer"),
    Sunset(R.drawable.icon_sunset, "Atardecer"),
    Unknown(-1, "")
}
