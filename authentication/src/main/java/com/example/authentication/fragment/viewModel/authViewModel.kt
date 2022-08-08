package com.example.authentication.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.core.domain.error.ErrorEntity
import com.example.core.domain.Result
import com.example.core.domain.product
import com.example.core.domain.user
import com.example.core.usecases.login
import com.example.core.usecases.registrate
import kotlinx.coroutines.launch
import javax.inject.Inject

class authViewModel @Inject constructor(
    private val loginUser: login,
    private val registrateUser: registrate,
) : ViewModel() {

    private val loginState = MutableLiveData<Result<user>> ()
    val _loginState: LiveData<Result<user>>
        get() = loginState

    fun login(login: String, password: String) {
        viewModelScope.launch {
            loginState.postValue(
                loginUser.execute(user("", "", login, "", "", "", null, null, password))
            )
        }
    }

    fun registrate(
        name: String,
        surname: String,
        login: String,
        photo: String?,
        telephone: String,
        lotsList: List<product>?,
        purchaseList: List<product>?,
        password: String
    ) {

        val loginRegex = Regex(pattern = "^(?=[a-zA-Z0-9._]{8,20}\$)(?!.*[_.]{2})[^_.].*[^_.]\$")
        if (!loginRegex.matches(login)) {
            loginState.postValue(Result.Error(ErrorEntity.WrongCredentialsLogin))
            return
        }
        val emailRegex = Regex(pattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\\\.)(.{1,})")
        if (!loginRegex.matches(login)) {
            loginState.postValue(Result.Error(ErrorEntity.WrongCredentialsLogin))
            return
        }
        //      loginState.postValue(loginUser.login(user))
    }
}
