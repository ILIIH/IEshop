package com.example.ieshop.framework.repository

import android.util.Log
import com.example.core.data.repository.repository
import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.ieshop.framework.sourse.entities.User
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import com.example.ieshop.utils.asUserData
import com.example.ieshop.utils.asUserDomain
import com.example.ieshop.utils.networkBoundResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

open class shopRepository @Inject constructor(
    val localDB: LocalDatabase,
    val remoteDB: ShopService,
    val userManager: userManager
) : repository {

    override suspend fun registrate(user: user) = networkBoundResource(
        query = {
            Log.i("RepoLog", "Inside query")
            flow {
                val resQuery = localDB.userDao().getUserInfo(user.login)
                if(!resQuery.isEmpty()) emit(resQuery.first().asUserData())
                else emit(user("-","-","-","-","-","-", listOf(), listOf(),"-","-"))
            }.flowOn(Dispatchers.IO)
        },
        fetch = {

      withContext(Dispatchers.IO){
          Log.i("RepoLog", "Inside fetch")

          val res = localDB.userDao().getUserInfo(user.login)
          Log.i("RepoLog", "Inside FLOW query res = ${res}")

          if (res.isEmpty()) {

              remoteDB.registrate(user.name,user.surname,user.email,user.login,user.photo,
                  user.telephone,user.password,user.country)

          } else throw  Exception("RepeatCredentials");
      }

        },
        saveFetchResult = { user ->
            withContext(Dispatchers.IO) {
                Log.i("RepoLog", "Inside saveFetchResult user : ${user.body()}")
                if(user.body()!=null) {
                    localDB.userDao().registrate(user.body()!!)
                    Log.i("RepoLog", "Inside fetch query emit user ${user} ")
                    user.body()?.let { userManager.login(it) }
                }
                else{
                    throw  Exception("Sever error");
                }
            }
        }
    )

    override suspend fun getUser(login: String): Flow<UIState<user>> {
        return flow { emit(UIState.Success(user("-","-","-","-","-","-", listOf(), listOf(),"-","-" ))) }
    }

    override suspend fun login(login: String, password: String) = networkBoundResource(
        query = {
            localDB.userDao().getUserInfo(login).asFlow().map { it.asUserData() }
        },
        fetch = {
            remoteDB.getUserInfo(login)
        },
        saveFetchResult = { user ->
            user.body()?.first()?.let { userManager.login(it) }
        }

    )

    override fun getUsersByPage(page: Int): List<user> {
        return listOf()
    }

    override fun getPurchaseByPage(page: Int): List<product> {
        return listOf()
    }
}
