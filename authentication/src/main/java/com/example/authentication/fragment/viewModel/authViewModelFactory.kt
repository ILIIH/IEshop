package com.example.authentication.fragment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.useCases.loginUser
import com.example.core.useCases.registrateUser
import javax.inject.Inject
import javax.inject.Provider

class authViewModelFactory
    @Inject constructor(
    private val loginUser: Provider<loginUser>,
    private val registrateUser: Provider<registrateUser>,
    ) : ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass ==authViewModel::class.java)
        return authViewModel(loginUser.get(),registrateUser.get()) as T
    }
}