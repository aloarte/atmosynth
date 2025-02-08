package com.devalr.dayweather.interactions

sealed class Event {
    data object LoadScreen : Event()

    data object ChangeCity : Event()
}
