package com.android.presentation.splash

import androidx.lifecycle.viewModelScope
import com.android.core.base.BaseVM
import com.android.core.base.BaseViewState
import com.android.core.events.NotifyEvents
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@HiltViewModel
class SplashViewModel @Inject constructor() : BaseVM<BaseViewState<SplashUiState>, SplashAction>() {

    override fun onTriggerEvent(action: SplashAction) {
        when (action) {
            SplashAction.OnOpenLogin -> sendEvent(NotifyEvents.Navigate("login"))
            SplashAction.ShowProgressAndMove -> showFakeProgress()
            SplashAction.CallApi -> hitApi()
        }
    }

    private fun hitApi() {
        viewModelScope.launch {
            sendEvent(NotifyEvents.ToggleLoading(true))
            delay(5000)
            sendEvent(NotifyEvents.ToggleLoading(false))
            sendEvent(NotifyEvents.ShowError(title = "Test", message = "Some error occurred"))
        }
    }

    private fun showFakeProgress() {
        viewModelScope.launch {
            sendEvent(NotifyEvents.ToggleLoading(true))

            delay(5000)

            sendEvent(NotifyEvents.ToggleLoading(false))

            delay(500)

            sendEvent(NotifyEvents.Navigate("login"))
        }
    }
}
