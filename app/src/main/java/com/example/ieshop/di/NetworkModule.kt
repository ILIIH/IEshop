package com.example.ieshop.di

import com.example.ieshop.BuildConfig
import com.example.ieshop.framework.sourse.remoteSourse.ShopService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BaceURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun provideApi(retrofit: Retrofit): ShopService {
        return retrofit.create(ShopService::class.java)
    }

}
