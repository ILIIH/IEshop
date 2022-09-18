package com.example.ieshop.framework.repository

import com.example.core.domain.user
import com.example.ieshop.framework.sourse.entities.UserDatabase
import javax.inject.Inject

class userManager @Inject constructor() {
    private var currentUser: user? = null
    fun login(user: user){
        currentUser = user
    }
    fun isLogined() = currentUser!=null
    fun unlogin(){
        currentUser = null  }
}