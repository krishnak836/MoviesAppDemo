package com.krishna.task1app.di

import com.krishna.task1app.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.jetbrains.annotations.NotNull
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    fun providesUrl() = "http://myvbox.uk:2052/"

    @Provides
    @Singleton
    fun providesApiService(): ApiService {
        @NotNull
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(providesUrl())
            .build()
        return retrofit.create(ApiService::class.java)
    }
}