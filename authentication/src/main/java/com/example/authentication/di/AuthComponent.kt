package com.example.authentication.di

import android.app.Application
import android.content.Context
import com.example.authentication.fragment.login
import com.example.core.repository.repository
import com.example.core.useCases.loginUser
import com.example.core.useCases.registrateUser
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class authScope

@authScope
@Component(
    dependencies = [authDeps::class],
    modules = [authModule::class]
)
interface AuthComponent {
    fun inject(login: login)

    @Component.Builder
    interface Builder {
        fun deps(authDeps: authDeps): Builder
        fun build(): AuthComponent
    }
}
@Module
internal class authModule{
    @Provides
    fun provideLoginUsecase(repository: repository):loginUser {
        return loginUser(repository)
    }
    @Provides
    fun provideRegistrateUsecase(repository: repository):registrateUser {
        return registrateUser(repository)
    }
}

interface authDeps {
    val repository: repository
}

interface AuthDepsProvider {
    val deps: authDeps
}

val Context.authDepsProvider: AuthDepsProvider
    get() = when (this) {
        is AuthDepsProvider -> this
        is Application -> error("Application must implemets AuthDepsProvider")
        else -> applicationContext.authDepsProvider
    }
