package com.example.core.data.soure

import com.example.core.domain.product
import com.example.core.domain.user

interface dataBase {
    fun registrate(login: String, password: String): Boolean
    fun getUser(login: String): user
    fun getUsersByPage(page: Int): List<user>
    fun getPurchaseByPage(page: Int): List<product>
}