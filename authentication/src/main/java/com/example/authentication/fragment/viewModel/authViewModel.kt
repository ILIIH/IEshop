package com.example.authentication.fragment.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.core.model.ErrorEntity
import com.example.core.model.Result
import com.example.core.model.product
import com.example.core.model.user
import com.example.core.useCases.loginUser
import com.example.core.useCases.registrateUser
import javax.inject.Inject

class authViewModel @Inject constructor(
    private val loginUser: loginUser,
    private val registrateUser: registrateUser,
) : ViewModel() {

    private val loginState = MutableLiveData<Result<user>> ()
    val _loginState: LiveData<Result<user>>
        get() = loginState


    fun login(login: String, password: String){
        loginState.postValue(
            loginUser.login(user("","",login,"","","",null,null,password))
        )
    }

    fun registrate(name: String,
              surname: String,
              login: String,
              photo: String?,
              telephone: String,
              lotsList: List<product>?,
              purchaseList: List<product>?,
              password: String) {

        val loginRegex = Regex(pattern = "^(?=[a-zA-Z0-9._]{8,20}\$)(?!.*[_.]{2})[^_.].*[^_.]\$")
        if(!loginRegex.matches(login)){
            loginState.postValue(Result.Error(ErrorEntity.WrongCredentialsLogin))
            return
        }
        val emailRegex = Regex(pattern = "^[A-Za-z](.*)([@]{1})(.{1,})(\\\\.)(.{1,})")
        if(!loginRegex.matches(login)){
            loginState.postValue(Result.Error(ErrorEntity.WrongCredentialsLogin))
            return
        }
  //      loginState.postValue(loginUser.login(user))

    }
}
