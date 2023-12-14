package com.example.authentication.di

import android.app.Application
import android.content.Context
import com.example.authentication.fragment.ConfirmationFragment
import com.example.authentication.fragment.LoginFragment
import com.example.authentication.fragment.RegistrateFragment
import com.example.core.data.repository.repository
import com.example.core.usecases.login
import com.example.core.usecases.registrate
import com.example.core.usecases.validateEmail
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

    fun injectLogin(login: LoginFragment)
    fun injectRegistrate(registrate: RegistrateFragment)
    fun injectConfirmation(confirm: ConfirmationFragment)

    @Component.Builder
    interface Builder {
        fun deps(authDeps: authDeps): Builder
        fun build(): AuthComponent
    }
}
@Module
internal class authModule {
    @Provides
    fun provideLoginUsecase(repository: repository) = login(repository)

    @Provides
    fun provideRegistrateUsecase(repository: repository) =  registrate(repository)

    @Provides
    fun provideAuthorizeUsecase(repository: repository) = validateEmail(repository)

}

interface authDeps {
    val repository: repository
}

interface AuthDepsProvider {
    val authDeps: authDeps
}

val Context.authDepsProvider: AuthDepsProvider
    get() = when (this) {
        is AuthDepsProvider -> this
        is Application -> error("Application must implemets AuthDepsProvider")
        else -> applicationContext.authDepsProvider
    }
