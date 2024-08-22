package com.android.domain.di

import com.android.domain.repository.AuthRepo
import com.android.domain.usecase.AuthUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideAuthUseCase(
        repository: AuthRepo,
    ): AuthUseCase {
        return AuthUseCase(repository)
    }
}