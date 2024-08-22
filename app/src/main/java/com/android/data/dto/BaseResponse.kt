package com.android.data.dto

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Parcelize
open class BaseResponse(
    val status: Boolean? = false,
    val message: ApiMessageData = ApiMessageData(),
) : Parcelable

@Parcelize
data class ApiMessageData(
    val errorCode: String? = null,
    val title: String? = "",
    val description: String? = "",
) : Parcelable
