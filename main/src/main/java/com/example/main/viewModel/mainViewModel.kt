package com.example.authentication.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.usecases.getAllProducts
import com.example.core.usecases.getFavoriteProducts
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class mainViewModel @Inject constructor(
    private val getAllProducts: getAllProducts,
    private val getFavoriteProducts: getFavoriteProducts
) : ViewModel() {

    private val chategoryList: List<product> = listOf(
        product("Hovers",20,"Hovers","", listOf("https://i.postimg.cc/xC05VBnD/speaker-img.png"),"C",1),
        product("Clothes",1,"Clothes","", listOf("https://i.postimg.cc/xC05VBnD/speaker-img.png"),"C",1),
        product("Instruments",0,"Instruments","", listOf("https://i.postimg.cc/xC05VBnD/speaker-img.png"),"C",1),
    )

    fun updateChategory( setChategory:(list: List<product>) ->Unit){
        setChategory(chategoryList)
    }

    private val productLoadingState = MutableLiveData<UIState<List<product>>> ()
    val _productLoadingState: LiveData<UIState<List<product>>>
        get() = productLoadingState

    private val productFavLoadingState = MutableLiveData<UIState<List<product>>> ()
    val _productFavLoadingState: LiveData<UIState<List<product>>>
        get() = productFavLoadingState

    init {

        viewModelScope.launch {
            getAllProducts.execute().collect {
                productLoadingState.postValue(it)
            }
            getFavoriteProducts.execute().collect {
                productFavLoadingState.postValue(it)
            }
        }
    }
}
