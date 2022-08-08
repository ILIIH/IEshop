package com.example.core.usecases

import com.example.core.domain.user
import com.example.core.data.repository.repository

class registrate constructor(private val repository: repository) {

    fun execute(user: user): Boolean {
        val encryptedPassword = StringBuffer()
        for (item in user.password) {
            encryptedPassword.append(item.code + 11)
        }
        return repository.registrate(user.login, user.name, user.surname, user.login, user.photo, user.telephone, encryptedPassword.toString())
    }
}
