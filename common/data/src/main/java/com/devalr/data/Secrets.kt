package com.devalr.data

import android.content.Context
import android.content.res.AssetManager

class Secrets(
    context: Context,
) {
    init {
        System.loadLibrary("secrets")
    }

    private external fun getAemetApiKeyFromNative(assetManager: AssetManager): String

    private external fun getGeminiApiKeyFromNative(assetManager: AssetManager): String

    private val assetManager: AssetManager = context.assets

    fun getAemetApiKey(): String = getAemetApiKeyFromNative(assetManager)

    fun getGeminiApiKey(): String = getGeminiApiKeyFromNative(assetManager)
}
