package com.android.domain.di

import com.android.data.repository.auth.AuthRepoImpl
import com.android.domain.repository.AuthRepo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
@Module
@InstallIn(SingletonComponent::class)
fun interface RepositoryModule {

    @Binds
    @Singleton
    fun bindMyRepository(
        repoImpl: AuthRepoImpl
    ): AuthRepo
}