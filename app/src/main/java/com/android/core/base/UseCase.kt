package com.android.core.base

import com.android.data.remote.common.ApiResult
import kotlinx.coroutines.flow.Flow


/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
abstract class BaseUseCase<in Params, Result>() {
    operator fun invoke(params: Params): Flow<ApiResult<Result>> {
        return execute(params)
    }

    protected abstract fun execute(requestData: Params): Flow<ApiResult<Result>>
}
