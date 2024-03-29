package com.example.authentication.fragment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.authentication.di.*

class authComponentViewModel(application: Application) : AndroidViewModel(application) {

    val authComponent: AuthComponent by lazy {
        DaggerAuthComponent.builder().deps(application.authDepsProvider.authDeps).build()
    }
}
