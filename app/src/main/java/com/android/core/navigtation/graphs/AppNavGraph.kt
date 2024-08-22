package com.android.core.navigtation.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.android.core.navigtation.anim.screenFadeIn
import com.android.core.navigtation.anim.screenFadeOut
import com.android.core.navigtation.anim.screenSlideIn
import com.android.core.navigtation.anim.screenSlideOut
import com.android.core.providers.dialog.DialogHost
import com.android.presentation.login.navigation.loginScreen
import com.android.presentation.splash.navigation.SplashScreen
import com.android.presentation.splash.navigation.splashScreen

/**
 * Created by ThulasiRajan.P on 7/8/2024
 */
@Composable
fun AppNavGraph(mainController: NavHostController) {
    NavHost(
        navController = mainController,
        startDestination = SplashScreen,
        enterTransition = { screenSlideIn() },
        exitTransition = { screenFadeOut() },
        popEnterTransition = { screenFadeIn() },
        popExitTransition = { screenSlideOut() },
    ) {
        splashScreen()

        loginScreen()
    }

    DialogHost()
}
