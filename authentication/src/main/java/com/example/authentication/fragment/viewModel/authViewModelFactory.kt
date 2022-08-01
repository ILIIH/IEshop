package com.example.authentication.fragment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class authViewModelFactory @Inject constructor( authViewModelProvider: Provider<authViewModel>) : ViewModelProvider.Factory{
    private val providers = mapOf<Class<*>, Provider<out ViewModel>>(
        authViewModel::class.java to authViewModelProvider,
    )

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return providers[modelClass]!!.get() as T
    }
}