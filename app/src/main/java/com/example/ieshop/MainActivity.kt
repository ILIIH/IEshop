package com.example.ieshop

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.authentication.di.AuthDepsProvider
import com.example.authentication.di.authDeps
import com.example.ieshop.di.AppComponent
import com.example.ieshop.di.DaggerAppComponent

class MainActivity : AppCompatActivity(), AuthDepsProvider {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }
    override val deps: authDeps = appComponent
}
