package com.example.ieshop.framework.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.core.data.repository.repository
import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.ieshop.di.AppScope
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import com.example.ieshop.utils.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

@AppScope
open class shopRepository @Inject constructor(
    val localDB: LocalDatabase,
    val remoteDB: ShopService
) : repository {

    private var curUser = MutableLiveData<user>()
    val _curUser: LiveData<user>
        get() = curUser

    override fun getCurrentUser() = _curUser.value

    override suspend fun authorize(email: String, code: String, user: user) {
        withContext(Dispatchers.IO) {
            Log.i("RepoLog", "inside authorize repository")
            return@withContext remoteDB.authorizeEmail(email, code)
        }
    }

    override suspend fun registrate(user: user) = networkBoundResource(
        query = {
            Log.i("RepoLog", "Inside query")
            flow {
                val resQuery = localDB.userDao().getUserInfo(user.email)

                if (resQuery.isNotEmpty()) emit(resQuery.first().asUserData())
                else emit(user("-", "-", "-", "-", "-", "-", listOf(), listOf(), "-", "-"))
            }.flowOn(Dispatchers.IO)
        },
        fetch = {
            withContext(Dispatchers.IO) {
                val res = localDB.userDao().getUserInfo(user.login)
                Log.i("RepoLog", "Inside FLOW query res = $res")

                if (res.isEmpty()) {
                    remoteDB.registrate(
                        user.name,
                        user.surname,
                        user.email,
                        user.login,
                        user.photo,
                        user.telephone,
                        user.password,
                        user.country
                    )
                } else throw Exception("RepeatCredentials")
            }
        },
        saveFetchResult = { user ->
            withContext(Dispatchers.IO) {
                Log.i("RepoLog", "Inside saveFetchResult user : ${user.body()}")
                if (user.body() != null) {
                    localDB.userDao().registrate(user.body()!!.asUserDatabace())
                    Log.i("RegisterCheck", "!!!!Inside Fetrch regiotreted !!!! ${user.body()!!.asUserDatabace()}")

                    val resQuery = localDB.userDao().getUserInfo(user.body()!!.login)
                    Log.i("RegisterCheck", "!!!!Inside Fetrch regiotreted  get user =  !!!! $resQuery")
                } else {
                    throw Exception("Sever error")
                }
            }
        }
    )

    override suspend fun getUser(login: String): Flow<UIState<user>> {
        return flow { emit(UIState.Success(user("-", "-", "-", "-", "-", "-", listOf(), listOf(), "-", "-"))) }
    }

    override suspend fun login(login: String, password: String) = networkBoundResource(
        query = {
            Log.i("RepoLog", "PASSWORD =  $password")
            flow {
                Log.i("RepoLog", "Inside login query ")
                val res = localDB.userDao().getUserInfo(login)
                Log.i("RepoLog", "Inside login query $res")

                if (res.isEmpty())emit(user("-", "-", "-", "-", "-", "-", listOf(), listOf(), "-", "-"))
                else emit(res.first().asUserData())
            }.flowOn(Dispatchers.IO)
        },
        fetch = {
            Log.i("RepoLog", "Inside login fetch ")

            remoteDB.login(login, password)
        },
        saveFetchResult = { user ->
            Log.i("RepoLog", "Inside login saveFetchResult user =  " + user.body())

            if (user.body() != null) {
                if (user.body()!!.isEmpty()) throw Exception("Incorrect password or login")
                curUser.postValue(user.body()!!.first().asUserData())
            } else throw Exception("Sever error")
        }

    )

    override fun getUsersByPage(page: Int): List<user> {
        return listOf()
    }

    override fun getProductsByPage(page: Int): List<product> {
        return listOf()
    }
    override suspend fun getFavoriteProduct() =  networkBoundResource(
        query = {
            Log.i("RepoLog", "Inside Favour Product query")
            flow {
                val resQuery = localDB.userDao().getAllProducts()
                Log.i("RepoLog", "prod + $resQuery")
                if (resQuery.isNotEmpty()) emit(resQuery.map { it.asProductData() })
                else emit(listOf())
            }.flowOn(Dispatchers.IO)
        },
        fetch = {
            withContext(Dispatchers.IO) {
                Log.i("RepoLog", "Inside Favour product fetch ")
                remoteDB.getFavoriteProduct(curUser.value!!.login)
            }
        },
        saveFetchResult = {
            withContext(Dispatchers.IO) {
                Log.i("RepoLog", "Inside Favour product saveFetchResult body =  " + it.body())

                if (it.body() != null) {
                    if (it.body()!!.isEmpty()) throw Exception("Incorrect password or login")
                    it.body()!!.map { localDB.userDao().addProduct(it.asProductDataBace()) }
                } else throw Exception("Sever error")
            }
        }
    )

    override suspend fun getAllProduct() = networkBoundResource(
        query = {
            Log.i("RepoLog", "Inside Product query")
            flow {
                val resQuery = localDB.userDao().getAllProducts()
                Log.i("RepoLog", "prod + $resQuery")
                if (resQuery.isNotEmpty()) emit(resQuery.map { it.asProductData() })
                else emit(listOf())
            }.flowOn(Dispatchers.IO)
        },
        fetch = {
            withContext(Dispatchers.IO) {
                Log.i("RepoLog", "Inside product fetch ")
                remoteDB.getAllProduct()
            }
        },
        saveFetchResult = {
            withContext(Dispatchers.IO) {
                Log.i("RepoLog", "Inside product saveFetchResult body =  " + it.body())

                if (it.body() != null) {
                    if (it.body()!!.isEmpty()) throw Exception("Incorrect password or login")
                    it.body()!!.map { localDB.userDao().addProduct(it.asProductDataBace()) }
                } else throw Exception("Sever error")
            }
        }
    )
}
