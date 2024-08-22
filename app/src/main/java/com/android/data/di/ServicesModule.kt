package com.android.data.di

import com.android.data.remote.AuthService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
@Module
@InstallIn(SingletonComponent::class)
class ServicesModule {

    @Provides
    @Singleton
    fun provideAuthService(
        retrofit: Retrofit,
    ): AuthService {
        return retrofit.create(AuthService::class.java)
    }
}