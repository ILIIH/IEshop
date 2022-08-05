package com.example.ieshop.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Provides
    fun provideRoom(application: Application): LocalDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            LocalDatabase::class.java,
            "user_shop_database"

        )
            .fallbackToDestructiveMigration() // если после миграции не нужно схоранять базу
            .build()
    }
}
