package com.example.ieshop.di

import android.app.Application
import com.example.authentication.di.AuthDepsProvider
import com.example.authentication.di.authDeps
import com.example.main.di.MainDepsProvider
import com.example.main.di.mainDeps
import com.example.main.di.mainDepsProvider

open class MyApplication : Application(), AuthDepsProvider, MainDepsProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }
    override val authDeps: authDeps = appComponent
    override val mainDeps: mainDeps = appComponent

}
