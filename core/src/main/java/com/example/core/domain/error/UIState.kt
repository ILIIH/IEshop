package com.example.core.domain.error

sealed class UIState<T>(
    val data: T? = null,
    val error: ErrorEntity? = null
) {
    class Success<T>(data: T? = null) : UIState<T>(data)
    class Loading<T>(data: T? = null) : UIState<T>(data)
    class Error<T>(throwable: ErrorEntity, data: T? = null) : UIState<T>(data, throwable)
}
