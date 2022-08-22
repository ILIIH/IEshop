package com.example.ieshop.framework.repository

import com.example.core.data.repository.repository
import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import com.example.ieshop.utils.networkBoundResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class shopRepository @Inject constructor(
    val localDB: LocalDatabase,
    val remoteDB: ShopService,
    val userManager: userManager
) : repository {

    override fun registrate(user: user): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getUser(login: String) = networkBoundResource(
        query = {
            localDB.userDao().getUserInfo(login).asFlow()
        },
        fetch = {
            localDB.userDao().getUserInfo(login)
            // remoteDB.getUserInfo(login)
        },
        saveFetchResult = { user ->
            userManager.login(user.first())
        }

    ).stateIn(scope = CoroutineScope(Dispatchers.IO)).map { result ->
        when (result) {
            is UIState.Success -> true
            is UIState.Error -> false
            is UIState.Loading -> false
        }
    }

    override suspend fun login(login: String, password: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun getUsersByPage(page: Int): List<user> {
        TODO("Not yet implemented")
    }

    override fun getPurchaseByPage(page: Int): List<product> {
        TODO("Not yet implemented")
    }
}
