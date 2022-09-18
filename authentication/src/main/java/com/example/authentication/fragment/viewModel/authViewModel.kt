package com.example.authentication.fragment.viewModel

import android.text.TextUtils
import android.util.Patterns
import androidx.lifecycle.*
import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.error.UIState
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.core.usecases.login
import com.example.core.usecases.registrate
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject


class authViewModel @Inject constructor(
    private val loginUser: login,
    private val registrateUser: registrate
) : ViewModel() {

    private val loginState = MutableLiveData<UIState<user>> ()
    val _loginState: LiveData<UIState<user>>
        get() = loginState

    fun login(login: String, password: String) {
        viewModelScope.launch {
                loginUser.execute(user("", "", login, "", "", "",
                    listOf(), listOf(), password, "")).collect {
                    loginState.postValue(it)
                    }
        }
    }


    fun checkEmail(email: CharSequence?): Boolean {
        return !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    fun registrate(
        name: String,
        surname: String,
        login: String,
        photo: String,
        telephone: String,
        lotsList: List<product>,
        purchaseList: List<product>,
        password: String,
        email: String,
        country: String
    ) {
        if (login.length<3) loginState.postValue(UIState.Error(ErrorEntity.WrongCredentialsLogin()))
        else if (!checkEmail(email)) loginState.postValue(UIState.Error(ErrorEntity.WrongCredentialsLogin()))
        else
            viewModelScope.launch {
                registrateUser.execute(
                    user(
                        name = name,
                        surname = surname,
                        login = login,
                        email = email,
                        photo = photo,
                        telephone = telephone,
                        lotsList = lotsList,
                        purchaseList = purchaseList,
                        password = password,
                        country = country
                    )
                ).collect {
                   loginState.postValue(it)
                }
            }
    }
}
