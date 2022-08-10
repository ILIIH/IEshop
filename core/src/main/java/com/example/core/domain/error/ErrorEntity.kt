package com.example.core.domain.error

sealed class ErrorEntity {
    object Network : ErrorEntity()

    object WrongCredentialsLogin : ErrorEntity()

    object WrongCredentialsEmail : ErrorEntity()

    object WrongCredentialsPassword : ErrorEntity()

    object WrongLoginOrPass : ErrorEntity()

    object MissToken : ErrorEntity()

    object RepeatCredentials : ErrorEntity()

    object DatabaceError : ErrorEntity()

}

