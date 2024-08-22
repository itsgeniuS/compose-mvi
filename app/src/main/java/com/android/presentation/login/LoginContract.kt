package com.android.presentation.login

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import kotlinx.parcelize.Parcelize

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Composable
fun LoginContract(
    onLoggedIn: () -> Unit,
) {

}

@Stable
@Parcelize
data class LoginUiState(
    val email: String? = null,
    val password: String? = null,
) : Parcelable

sealed interface LoginAction {
    data object OnSubmit : LoginAction

    data class OnEnterEmail(
        val email: String
    ) : LoginAction

    data class OnEnterPassword(
        val password: String
    ) : LoginAction
}

