package com.example.core.model

sealed class ErrorEntity {
    object Network : ErrorEntity()

    object WrongCredentials : ErrorEntity()

    object MissToken : ErrorEntity()

    object RepeatCredentials : ErrorEntity()

    object DatabaceError : ErrorEntity()
}
