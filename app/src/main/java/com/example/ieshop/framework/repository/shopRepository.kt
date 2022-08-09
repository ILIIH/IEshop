package com.example.ieshop.framework.repository

import android.util.Log
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.core.data.repository.repository
import com.example.ieshop.framework.sourse.entities.User
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import com.example.ieshop.utils.checkInternet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class shopRepository @Inject constructor(val localDB: LocalDatabase, val remoteDB: ShopService) : repository {

    override fun registrate(
        username: String,
        name: String,
        surname: String,
        login: String,
        photo: String,
        telephone: String,
        password: String,
        country:String
    ): Boolean {
        val connection = checkInternet()
        Log.i("TestingApp", "Internet = $connection")
        return return if (connection) {
            if(remoteDB.countOfUser(login).body()?.isNotEmpty() == true){
                remoteDB.registrate(username, name, surname, login, photo, telephone, password,country).isSuccessful
                true
            } else false

        } else {
            if(localDB.userDao().countOfUser(login) != 0){
                localDB.userDao().registrate(User( name, surname, login, photo, telephone, password,country))
                true
            } else false
        }
    }

    override suspend fun getUser(login: String): Boolean {
        val connection = checkInternet()
        Log.i("TestingApp", "Internet = $connection")
        return if (connection) {
            remoteDB.getUserInfo(login).body()!!.isNotEmpty()
        } else {
            localDB.userDao().getUserInfo(login).isNotEmpty()
        }
    }

    override suspend fun login(login: String, password: String): Boolean {
        return withContext(Dispatchers.IO) {
            val connection = checkInternet()
            Log.i("TestingApp", "Internet = $connection")
            return@withContext if (connection) {
                remoteDB.login(login, password).isSuccessful
            } else {
                localDB.userDao().login(login, password) > 0
            }
        }

    }

    override fun getUsersByPage(page: Int): List<user> {
        TODO("Not yet implemented")
    }

    override fun getPurchaseByPage(page: Int): List<product> {
        TODO("Not yet implemented")
    }
}
