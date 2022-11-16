package com.example.authentication.fragment.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.main.di.DaggerMainComponent

import com.example.main.di.MainComponent
import com.example.main.di.mainDepsProvider

class mainComponentViewModel(application: Application) : AndroidViewModel(application) {

    val mainComponent: MainComponent by lazy {
        DaggerMainComponent.builder().deps(application.mainDepsProvider.mainDeps).build()
    }
}
