package com.example.ieshop.di

import android.content.Context
import com.example.authentication.di.authDeps
import com.example.core.repository.repository
import com.example.ieshop.framework.repository.shopRepository
import dagger.Binds
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RoomModule::class, RepoModule::class])
interface AppComponent : authDeps {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
    override val repository: repository
}

@Module
abstract class RepoModule {
    @Binds
    abstract fun bindRepo(shopRepository: shopRepository): repository
}
