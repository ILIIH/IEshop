package com.example.ieshop.framework.repository

import com.example.ieshop.framework.sourse.entities.User
import javax.inject.Inject

class userManager @Inject constructor() {
    private var currentUser: User? = null
    fun login(user: User){
        currentUser = user
    }
    fun isLogined() = currentUser!=null
    fun unlogin(){
        currentUser = null  }
}