package com.example.core.useCases

import com.example.core.model.user
import com.example.core.repository.repository

class registrateUser constructor(private val repository: repository) {

    fun registrate(user: user): Boolean {
        val encryptedPassword = StringBuffer()
        for (item in user.password) {
            encryptedPassword.append(item.code + 11)
        }
        return repository.registrate(user.login, user.name, user.surname, user.login, user.photo, user.telephone, encryptedPassword.toString())
    }
}
