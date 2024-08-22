package com.android.data.remote

import com.android.data.dto.response.LoginResponse
import com.android.data.remote.common.Api
import com.android.data.remote.common.ApiResult
import com.android.data.remote.common.ApiType
import com.google.gson.JsonObject
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
fun interface AuthService {

    @POST("login")
    @Api(ApiType.APP_URL)
    suspend fun login(
        @Body credentials: JsonObject,
    ): LoginResponse

}