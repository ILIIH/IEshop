package com.example.ieshop.di

import android.content.Context
import androidx.room.Room
import com.example.ieshop.framework.sourse.localSourse.LocalDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {

    @Singleton
    @Provides
    fun provideRoom(context: Context): LocalDatabase {
        return Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            "user_shop_database"

        )
            .fallbackToDestructiveMigration() // если после миграции не нужно схоранять базу
            .build()
    }
}
