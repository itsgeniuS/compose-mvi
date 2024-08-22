package com.android.presentation.splash

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.hilt.navigation.compose.hiltViewModel
import com.android.core.base.BaseScreen

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Composable
fun SplashContract() {
    val viewModel: SplashViewModel = hiltViewModel<SplashViewModel>()

    BaseScreen(
        notifyEventsChannel = viewModel.notifyEventsChannel,
        content = {
            SplashScreen(
                onAction = { event ->
                    viewModel.onTriggerEvent(event)
                }
            )
        },
    )
}

@Stable
data object SplashUiState

sealed interface SplashAction {
    data object OnOpenLogin : SplashAction
    data object ShowProgressAndMove: SplashAction
    data object CallApi: SplashAction
}
