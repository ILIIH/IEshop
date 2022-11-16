package com.example.main.di

import android.app.Application
import android.content.Context
import com.example.core.data.repository.repository
import com.example.core.usecases.getAllProducts
import com.example.core.usecases.getFavoriteProducts
import com.example.main.HomeFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Scope

@Scope
annotation class mainScope

@mainScope
@Component(
    dependencies = [mainDeps::class],
    modules = [mainModule::class]
)
interface MainComponent {

    fun injectHome(home: HomeFragment)

    @Component.Builder
    interface Builder {
        fun deps(authDeps: mainDeps): Builder
        fun build(): MainComponent
    }
}

@Module
internal class mainModule {
    @Provides fun provideGetAllProductsUsecase(repository: repository) = getAllProducts(repository)
    @Provides fun provideGetFavProductUsecase(repository: repository) = getFavoriteProducts(repository)
}

interface mainDeps {
    val repository: repository
}

interface MainDepsProvider {
    val mainDeps: mainDeps
}

val Context.mainDepsProvider: MainDepsProvider
    get() = when (this) {
        is MainDepsProvider -> this
        is Application -> error("Application must implemets AuthDepsProvider")
        else -> applicationContext.mainDepsProvider
    }
