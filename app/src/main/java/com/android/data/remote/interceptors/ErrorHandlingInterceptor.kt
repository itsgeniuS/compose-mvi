package com.android.data.remote.interceptors

import com.android.data.remote.common.AppException
import com.android.data.remote.common.NetworkMonitor
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
class ErrorHandlingInterceptor @Inject constructor(
    private val networkMonitor: NetworkMonitor,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!networkMonitor.isInternetAvailable()) {
            throw AppException.NoInternetException("No internet connection")
        }

        val request = chain.request()
        val response = chain.proceed(request)

        when (response.code) {
            401 -> throw AppException.UnauthorizedException("Unauthorized access")
            500 -> throw AppException.ServerErrorException("Server error")
        }

        return response
    }
}
