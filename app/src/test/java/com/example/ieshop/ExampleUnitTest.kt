package com.example.ieshop

import app.cash.turbine.test
import com.example.ieshop.framework.repository.shopRepository
import com.example.ieshop.framework.repository.userManager
import com.example.ieshop.framework.sourse.entities.User
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test


import org.junit.Assert.*
import org.junit.Before
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.mock
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {
    @Mock
    var LocalDb = mock<LocalDatabase>()
    var RemoteDb = mock<ShopService>()

    private lateinit var shopRepository: shopRepository
    @Before
    fun setUp(){
        Mockito.`when`(LocalDb.userDao().getUserInfo(anyString())).thenReturn(listOf(User("name",
            "Surname","email","login","photo","+380975350880","pass","UA")))
        Mockito.`when`(RemoteDb.getUserInfo(anyString())).thenReturn(Response.success(listOf(User("name",
            "Surname","email","login","photo","+380975350880","pass","UA"))))

    }

    @Test
    fun `should emit success`() = runBlocking {
        val userManager = userManager()
        shopRepository  = shopRepository(LocalDb,RemoteDb,userManager)
        shopRepository.getUser("login").test{
            assertEquals(true, awaitItem())
        }
    }


}

