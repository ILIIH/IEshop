package com.example.authentication.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.usecases.getAllProducts
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.publish
import kotlinx.coroutines.launch
import javax.inject.Inject

class mainViewModel @Inject constructor(
    private val getAllProducts: getAllProducts
) : ViewModel() {

    private val productLoadingState = MutableLiveData<UIState<List<product>>> ()
    val _productLoadingState: LiveData<UIState<List<product>>>
        get() = productLoadingState

    fun loadAllProduct() {
        viewModelScope.launch {
            getAllProducts.execute().collect{
                productLoadingState.postValue(it)

            }
        }
    }
}
