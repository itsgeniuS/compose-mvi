package com.android.data.remote.common


sealed class ApiResult<T>(
    val data: T? = null,
    val error: AppException.CustomException? = null,
) {
    class Loading<T>(
        data: T? = null,
    ) : ApiResult<T>(data)

    class Success<T>(
        data: T,
    ) : ApiResult<T>(data)

    class Error<T>(
        error: AppException.CustomException? = null,
    ) : ApiResult<T>(null, error)
}
