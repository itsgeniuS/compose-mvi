package com.android.data.dto.response

import android.os.Parcelable
import com.android.data.dto.BaseResponse
import kotlinx.parcelize.Parcelize

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Parcelize
data class LoginResponse(
    val userName: String,
//    @SerializedName("user_name")
    val userId: String,
) : BaseResponse(), Parcelable