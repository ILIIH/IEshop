package com.example.core.domain.error

import android.content.res.Resources
import com.example.core.R

sealed class ErrorEntity {
    data class Network(val measage : String = "NetworkError"): ErrorEntity()

    data class WrongCredentialsLogin(val measage : String = "WrongCredentialsLoginError") : ErrorEntity()

    data class WrongCredentialsEmail(val measage : String = "WrongCredentialsEmailError") : ErrorEntity()

    data class WrongCredentialsPassword(val measage : String = "WrongCredentialsPasswordError") : ErrorEntity()

    data class WrongLoginOrPass(val measage : String = "Wrong login or password error") : ErrorEntity()

    data class NotSameCode(val measage : String = "Error incorrect code") : ErrorEntity()

    data class ServerError(val measage : String = "ServerError") : ErrorEntity()

    data class RepeatCredentials(val measage : String = "RepeatCredentialsError") : ErrorEntity()

    data class DatabaceError(val measage : String = "DatabaceError") : ErrorEntity()


}

