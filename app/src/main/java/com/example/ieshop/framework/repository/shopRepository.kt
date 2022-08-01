package com.example.ieshop.framework.repository

import android.util.Log
import com.example.core.model.product
import com.example.core.model.user
import com.example.core.repository.repository
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import com.example.ieshop.utils.checkInternet
import javax.inject.Inject

class shopRepository @Inject constructor(val localDB: LocalDatabase, val remoteDB: ShopService) : repository {

    override fun registrate(
        username: String,
        name: String,
        surname: String,
        login: String,
        photo: String?,
        telephone: String,
        password: String
    ): Boolean {
        val connection = checkInternet()
        Log.i("TestingApp", "Internet = $connection")
        return if (connection) {
            remoteDB.registrate(username, name, surname, login, photo, telephone, password)
        } else {
            localDB.userDao().countOfUser(name, surname, login, photo, telephone, password) != 0
        }
    }

    override fun getUser(login: String): user {
        TODO("Not yet implemented")
    }

    override fun login(login: String, password: String): user? {
        TODO("Not yet implemented")
    }

    override fun getUsersByPage(page: Int): List<user> {
        TODO("Not yet implemented")
    }

    override fun getPurchaseByPage(page: Int): List<product> {
        TODO("Not yet implemented")
    }
}
