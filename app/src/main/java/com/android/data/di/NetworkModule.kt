package com.android.data.di

import android.content.Context
import com.android.data.remote.common.NetworkMonitor
import com.android.data.remote.interceptors.BaseUrlInterceptor
import com.android.data.remote.interceptors.ErrorHandlingInterceptor
import com.android.data.remote.interceptors.HeaderInterceptor
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.GsonBuilder
import com.kkCasino.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private const val WRITE_TIME = 60L
    private const val READ_TIME = 60L
    private const val CONNECT_TIME = 60L
    private const val CONTENT_LENGTH = 250_000L

    @Provides
    @Singleton
    fun provideBaseUrlInterceptor(
    ): BaseUrlInterceptor {
        return BaseUrlInterceptor()
    }

    @Provides
    @Singleton
    fun provideLoggingInterceptor(
    ): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    @Provides
    @Singleton
    fun provideChuckerInterceptor(@ApplicationContext context: Context): ChuckerInterceptor {
        return ChuckerInterceptor.Builder(context)
            .collector(
                ChuckerCollector(
                    context = context,
                    showNotification = true,
                    retentionPeriod = RetentionManager.Period.ONE_HOUR
                )
            )
            .maxContentLength(CONTENT_LENGTH)
            .redactHeaders("Auth-Token", "Bearer")
            .alwaysReadResponseBody(true)
            .build()
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideNetworkMonitor(@ApplicationContext context: Context): NetworkMonitor {
        return NetworkMonitor(context)
    }

    @Provides
    @Singleton
    fun provideExceptionHandler(networkMonitor: NetworkMonitor): ErrorHandlingInterceptor {
        return ErrorHandlingInterceptor(networkMonitor)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        baseUrlInterceptor: BaseUrlInterceptor,
        loggingInterceptor: HttpLoggingInterceptor,
        chuckerInterceptor: ChuckerInterceptor,
        headerInterceptor: HeaderInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder().apply {
            writeTimeout(WRITE_TIME, TimeUnit.SECONDS)
            readTimeout(READ_TIME, TimeUnit.SECONDS)
            connectTimeout(CONNECT_TIME, TimeUnit.SECONDS)

            addInterceptor(baseUrlInterceptor)
            addInterceptor(headerInterceptor)

            if (BuildConfig.DEBUG) {
                addInterceptor(loggingInterceptor)
                addInterceptor(chuckerInterceptor)
            }
        }.build()
    }


    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(
                GsonConverterFactory.create(
                    GsonBuilder()
                        .enableComplexMapKeySerialization()
                        .serializeNulls()
                        .setPrettyPrinting()
                        .setLenient()
                        .create()
                )
            ).build()
    }
}
