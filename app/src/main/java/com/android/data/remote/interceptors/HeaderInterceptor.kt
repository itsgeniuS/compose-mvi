package com.android.data.remote.interceptors

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
class HeaderInterceptor @Inject constructor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val newRequest = originalRequest.newBuilder().apply {
            addHeader("Accept", "application/json")
            addHeader("Authorization", "BEARER TOKEN")
            addHeader("Language", "en")
        }

        return chain.proceed(newRequest.build())
    }
}