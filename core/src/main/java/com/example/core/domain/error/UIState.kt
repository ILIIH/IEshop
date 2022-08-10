package com.example.core.domain.error

sealed class UIState<T> {
    class Idle<T> : UIState<T>()
    class Loading<T> : UIState<T>()
    class Error<T>(val error: ErrorEntity) : UIState<T>()
    class Success<T>(val data: T) : UIState<T>()
}
