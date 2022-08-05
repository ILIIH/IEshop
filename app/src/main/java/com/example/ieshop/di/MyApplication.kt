package com.example.ieshop.di

import android.app.Application
import android.util.Log
import com.example.authentication.di.AuthDepsProvider
import com.example.authentication.di.authDeps

open class MyApplication : Application(), AuthDepsProvider {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder().application(this).build()
    }

    override val deps: authDeps = appComponent

}
