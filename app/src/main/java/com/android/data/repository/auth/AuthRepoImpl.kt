package com.android.data.repository.auth

import com.android.data.dto.response.LoginResponse
import com.android.data.remote.AuthService
import com.android.data.remote.common.ApiResult
import com.android.domain.repository.AuthRepo
import com.google.gson.JsonObject
import com.squareup.moshi.Json
import javax.inject.Inject

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
class AuthRepoImpl @Inject constructor(
    private val authService: AuthService,
) : AuthRepo {
    override suspend fun onLogin(): LoginResponse {
        return authService.login(JsonObject())
    }
}