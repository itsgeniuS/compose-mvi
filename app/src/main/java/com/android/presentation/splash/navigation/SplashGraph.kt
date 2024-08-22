package com.android.presentation.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.android.presentation.splash.SplashContract
import kotlinx.serialization.Serializable

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Serializable
data object SplashScreen

internal fun NavGraphBuilder.splashScreen() {
    composable<SplashScreen> {
        SplashContract()
    }
}
