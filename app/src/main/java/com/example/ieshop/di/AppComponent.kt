package com.example.ieshop.di

import android.app.Application
import android.content.Context
import com.example.authentication.di.authDeps
import com.example.core.repository.repository
import com.example.ieshop.framework.repository.shopRepository
import dagger.*
import javax.inject.Scope
import javax.inject.Singleton

@AppScope
@Component(modules = [NetworkModule::class, RoomModule::class, RepoModule::class])
interface AppComponent : authDeps {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
        fun build():AppComponent
    }
    override val repository: repository
}

@Module
abstract class RepoModule {
    @Binds
    abstract fun bindRepo(shopRepository: shopRepository): repository
}

@Scope
annotation class AppScope
