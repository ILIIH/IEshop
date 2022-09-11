package com.example.ieshop


import com.example.core.domain.error.UIState
import com.example.core.domain.user
import com.example.core.usecases.login
import com.example.core.usecases.registrate
import com.example.ieshop.framework.repository.shopRepository
import io.mockk.coEvery
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.jupiter.api.Test


class TestUsecases {


    @Test
    fun `passwords should be same`() = runBlocking {

        val repo  = mockk<shopRepository>()
        val TestPass = "MyPasswordToTest1234567890"
        val registerSlot = slot<user>()
        val loginPassSlot = slot<String>()
        val loginSlot = slot<String>()

        coEvery {repo.registrate(capture(registerSlot))} returns
            flow {
                emit(UIState.Success( user("-","-","-","-","-","-",
                    listOf(), listOf(),"-","-")))
            }

        coEvery {repo.login(capture(loginSlot),capture(loginPassSlot))} returns
                flow {
                    emit(UIState.Success( user("-","-","-","-","-","-",
                        listOf(), listOf(),"-","-")))
                }

        val registrate = registrate(repo)
        val login = login(repo)

        registrate.execute(user("-","-","-","-","-","-",
            listOf(), listOf(),TestPass,"-"))

          login.execute(user("-","-","-","-","-","-",
              listOf(), listOf(),TestPass,"-")
          )

        val registrPass = registerSlot.captured.password
        val loginPass = loginPassSlot.captured


        assertEquals(registrPass, loginPass)
    }

}