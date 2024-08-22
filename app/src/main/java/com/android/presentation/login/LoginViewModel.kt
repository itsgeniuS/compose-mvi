package com.android.presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.android.core.base.BaseVM
import com.android.core.base.BaseViewState
import com.android.domain.usecase.AuthUseCase
import com.android.presentation.login.helpers.LoginValidations
import dagger.hilt.android.lifecycle.HiltViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authUseCase: AuthUseCase,
) : BaseVM<BaseViewState<LoginUiState>, LoginAction>() {

    private val validator: LoginValidations = LoginValidations()

    private var state by mutableStateOf(LoginUiState())

    override fun onTriggerEvent(action: LoginAction) {
        when (action) {
            is LoginAction.OnEnterEmail -> state = state.copy(email = action.email)
            is LoginAction.OnEnterPassword -> state = state.copy(email = action.password)
            LoginAction.OnSubmit -> if (isAllValidData()) proceedToLogin()
        }
    }

    private fun isAllValidData(): Boolean {
        return when {
            validator.isEmailInValid(state.email.toString()) -> false
            validator.isPasswordInvalid(state.password) -> false
            else -> return true
        }
    }

    private fun proceedToLogin() {
        callApi(
            api = authUseCase(AuthUseCase.RequestData(state.email.toString())),
            onSuccess = { response ->
                Timber.e("Response Success ${response.data}")
            },
            onError = { error ->
                Timber.e("Response Error $error")
            },
        )
    }
}
