package com.devalr.dayweather.model.now

import com.devalr.dayweather.model.enums.WindDirectionText

data class WindState(val direction: WindDirectionText, val speed: Int)