package com.example.authentication.fragment.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.usecases.getAllProducts
import com.example.core.usecases.login
import com.example.core.usecases.registrate
import com.example.core.usecases.validateEmail
import javax.inject.Inject
import javax.inject.Provider

class mainViewModelFactory
@Inject constructor(
    private val getAllProducts: Provider<getAllProducts>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == mainViewModel::class.java)
        return mainViewModel(getAllProducts.get()) as T
    }
}
