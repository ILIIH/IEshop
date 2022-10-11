package com.example.profile.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.core.usecases.*
import javax.inject.Inject
import javax.inject.Provider

class profileViewModelFactory
@Inject constructor(
    private val getUserPurchase: Provider<getUserPurchase>,
    private val getUserInfo: Provider<getUserInfo>
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == profileViewModel::class.java)
        return profileViewModel(getUserPurchase.get(),getUserInfo.get() ) as T
    }
}
