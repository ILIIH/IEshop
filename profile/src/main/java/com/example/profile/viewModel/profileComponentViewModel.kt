package com.example.profile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.profile.di.DaggerProfileComponent
import com.example.profile.di.ProfileComponent
import com.example.profile.di.profileDepsProvider

class profileComponentViewModel(application: Application) : AndroidViewModel(application) {

    val profileComponent: ProfileComponent by lazy {
        DaggerProfileComponent.builder().deps(application.profileDepsProvider.profileDeps).build()
    }


}
