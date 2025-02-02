package com.devalr.data

object Secrets {
    init {
        System.loadLibrary("secrets")
    }

    external fun getApiKeyFromNative(): String
}
