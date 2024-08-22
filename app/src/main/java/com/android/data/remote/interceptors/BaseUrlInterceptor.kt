package com.android.data.remote.interceptors

import com.android.core.app.EnvironmentManager
import com.android.data.remote.common.Api
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.Interceptor
import okhttp3.Response
import retrofit2.Invocation
import java.io.IOException
import javax.inject.Inject

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
class BaseUrlInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val apiType = originalRequest
            .tag(Invocation::class.java)
            ?.method()
            ?.getAnnotation(Api::class.java)?.apiType
            ?: throw IOException("BRUH... You must add ApiType to your request method in interface...")

        val baseUrl = EnvironmentManager.getBaseUrl(apiType).toHttpUrl()

        val newUrl = originalRequest.url.newBuilder()
            .scheme(baseUrl.scheme)
            .host(baseUrl.host)
            .port(baseUrl.port)
            .build()

        val newRequest = originalRequest.newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(newRequest)
    }
}