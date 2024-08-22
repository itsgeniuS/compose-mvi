package com.android.presentation.login.helpers

import android.util.Patterns

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
class LoginValidations {

    companion object {
        private const val PASSWORD_MIN = 6
        private const val PASSWORD_MAX = 16
    }

    fun isEmailInValid(email: String?): Boolean {
        return email == null || Patterns.EMAIL_ADDRESS.matcher(email).matches().not()
    }

    fun isPasswordInvalid(password: String?): Boolean {
        return password == null || password.length !in PASSWORD_MIN..PASSWORD_MAX
    }
}
