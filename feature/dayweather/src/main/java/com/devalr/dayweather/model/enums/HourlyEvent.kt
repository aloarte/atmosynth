package com.devalr.dayweather.model.enums

import androidx.annotation.DrawableRes

enum class HourlyEvent(
    @DrawableRes val iconResource: Int,
) {
    Sunrise(/*R.drawable.*/-1),
    Sunset(/*R.drawable.*/-1),
}
