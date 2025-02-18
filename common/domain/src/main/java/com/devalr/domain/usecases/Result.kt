package com.devalr.domain.usecases

sealed class Result<out T> {
    data class Error(val exception: Throwable) : Result<Nothing>()
    data class Success<out T>(val data: T) : Result<T>()
}