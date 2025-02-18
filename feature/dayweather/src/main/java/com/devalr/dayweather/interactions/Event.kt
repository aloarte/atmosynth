package com.devalr.dayweather.interactions

sealed class Event {
    data object LoadScreen : Event()

    data object ChangeCity : Event()
    data class LaunchAiPrompt(val promptData:String) : Event()
    data class OnUploadErrorState(val error: Boolean) : Event()
    data class OnUploadLoadingState(val loading: Boolean) : Event()

}
