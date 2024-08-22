package com.android.data.dto.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Parcelize
data class LoginRequest(
    val emailOrPhone: String,
    val password: String,
//    @SerizalizedName("language_key")
    val language: String,
) : Parcelable
