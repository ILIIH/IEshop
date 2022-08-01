package com.example.core.soure

import com.example.core.model.product
import com.example.core.model.user

interface dataBase {
    fun registrate(login: String, password: String): Boolean
    fun getUser(login: String): user
    fun getUsersByPage(page: Int): List<user>
    fun getPurchaseByPage(page: Int): List<product>
}