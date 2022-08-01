package com.example.core.model

sealed class errorEntity {
    object Network : errorEntity()

    object Credentials : errorEntity()

    object MissToken : errorEntity()
}
