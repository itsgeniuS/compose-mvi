package com.android.domain.usecase

import androidx.annotation.Keep
import com.android.core.base.BaseUseCase
import com.android.data.dto.response.LoginResponse
import com.android.data.remote.common.ApiResult
import com.android.data.remote.common.AppException
import com.android.domain.repository.AuthRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
class AuthUseCase @Inject constructor(
    private val repository: AuthRepo,
) : BaseUseCase<AuthUseCase.RequestData, LoginResponse>() {

    @Keep
    data class RequestData(val someParameter: String)

    override fun execute(requestData: RequestData): Flow<ApiResult<LoginResponse>> = flow {
        try {
            emit(ApiResult.Loading())
            val response = repository.onLogin()
            emit(ApiResult.Success(response))
        } catch (exception: AppException.CustomException) {
            emit(ApiResult.Error(error = exception))
        }
    }
}