package com.example.profile.di

import android.app.Application
import android.content.Context
import com.example.core.data.repository.repository
import com.example.core.usecases.getAllProducts
import com.example.core.usecases.getFavoriteProducts
import com.example.core.usecases.getUserInfo
import com.example.core.usecases.getUserPurchase
import com.example.profile.ProfileFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class profileScope

@profileScope
@Component(
    dependencies = [profileDeps::class],
    modules = [profileModule::class]
)
interface ProfileComponent {

    fun injectProfile(profile: ProfileFragment)

    @Component.Builder
    interface Builder {
        fun deps(authDeps: profileDeps): Builder
        fun build(): ProfileComponent
    }
}

@Module
internal class profileModule {
    @Provides
    fun provideGetPurchase(repository: repository) = getUserPurchase(repository)
    @Provides
    fun provideGetUserInfo(repository: repository) = getUserInfo(repository)
}

interface profileDeps {
    val repository: repository
}

interface profileDepsProvider {
    val profileDeps: profileDeps
}

val Context.profileDepsProvider: profileDepsProvider
    get() = when (this) {
        is profileDepsProvider -> this
        is Application -> error("Application must implemets AuthDepsProvider")
        else -> applicationContext.profileDepsProvider
    }