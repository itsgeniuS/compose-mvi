package com.android.domain.repository

import com.android.data.dto.response.LoginResponse
import com.android.data.remote.common.ApiResult

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
fun interface AuthRepo {
    suspend fun onLogin(): LoginResponse
}