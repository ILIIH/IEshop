package com.example.profile.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.core.usecases.getAllProducts
import com.example.core.usecases.getFavoriteProducts
import com.example.core.usecases.getUserInfo
import com.example.core.usecases.getUserPurchase
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class profileViewModel @Inject constructor(
    private val getPurchase: getUserPurchase,
    private val getUserInfo: getUserInfo
) : ViewModel() {

    private val user = MutableLiveData<user> ()
    val _user: LiveData<user>
        get() = user

    private val purchaseLoadingState = MutableLiveData<UIState<List<product>>> ()
    val _purchaseLoadingState: LiveData<UIState<List<product>>>
        get() = purchaseLoadingState

    init {
        user.postValue(getUserInfo.execute())
        viewModelScope.launch {
            getPurchase.execute().collect{
                purchaseLoadingState.postValue(it)
            }
        }
    }

}
