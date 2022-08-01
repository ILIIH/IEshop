package com.example.core.model

sealed class result<T> {
    data class Success<T >(val data: T) : result<T>()

    data class Error<T >(val error: errorEntity) : result<T>()
}
