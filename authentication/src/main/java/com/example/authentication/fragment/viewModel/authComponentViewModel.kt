package com.example.authentication.fragment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.authentication.di.AuthComponent
import com.example.authentication.di.DaggerAuthComponent
import com.example.authentication.di.authDepsProvider

class authComponentViewModel(application: Application) : AndroidViewModel(application) {

    val authComponent: AuthComponent by lazy {
        DaggerAuthComponent.builder().deps(application.authDepsProvider.deps).build()
    }
}
