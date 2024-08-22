package com.android.core.base

/**
 * Created by ThulasiRajan.P on 8/8/2024
 */
sealed interface BaseViewState<out T> {
    data object Init : BaseViewState<Nothing>
    data object Loading : BaseViewState<Nothing>
    data object Empty : BaseViewState<Nothing>
    data class Data<T>(val value: T) : BaseViewState<T>
    data class Error(
        val title: String? = null,
        val message: String? = null,
        val errorCode: String? = null,
        val errorStr: String? = null,
    ) : BaseViewState<Nothing>
}