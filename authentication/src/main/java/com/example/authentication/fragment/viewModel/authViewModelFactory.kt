package com.example.authentication.fragment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.usecases.getUser
import com.example.core.usecases.login
import com.example.core.usecases.registrate
import com.example.core.usecases.validateEmail
import javax.inject.Inject
import javax.inject.Provider

class authViewModelFactory
@Inject constructor(
    private val loginUser: Provider<login>,
    private val registrateUser: Provider<registrate>,
    private val validateEmail: Provider<validateEmail>,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == authViewModel::class.java)
        return authViewModel(loginUser.get(), registrateUser.get(),validateEmail.get()) as T
    }
}
