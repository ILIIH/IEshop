package com.example.authentication.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.result
import com.example.core.model.user
import com.example.core.useCases.loginUser
import com.example.core.useCases.registrateUser
import javax.inject.Inject

class authViewModel @Inject constructor(
    private val loginUser: loginUser,
    private val registrateUser: registrateUser,
) : ViewModel() {

    private val loginState = MutableLiveData<result<user>> ()
    val _loginState: LiveData<result<user>>
        get() = loginState

    fun login(user: user) {
        loginState.postValue(loginUser.login(user))

    }
}
