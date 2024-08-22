package com.android.core.navigtation.graphs

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.android.core.navigtation.anim.screenFadeIn
import com.android.core.navigtation.anim.screenFadeOut
import com.android.core.navigtation.anim.screenSlideIn
import com.android.core.navigtation.anim.screenSlideOut
import com.android.presentation.dashboard.navigation.dashboardScreen

/**
 * Created by ThulasiRajan.P on 8/8/2024
 */

internal fun NavGraphBuilder.buildBottomScreens(
    navController: NavHostController,
) {
    composable(
        "bottom_bar_routes",
    ) {
        Scaffold(
            bottomBar = {

            },
            content = { paddingValues ->
                NavHost(
                    navController = navController,
                    modifier = Modifier.padding(
                        top = paddingValues.calculateTopPadding(),
                        bottom = paddingValues.calculateBottomPadding()
                    ),
                    startDestination = "dashboard",
                    enterTransition = { screenSlideIn() },
                    exitTransition = { screenFadeOut() },
                    popEnterTransition = { screenFadeIn() },
                    popExitTransition = { screenSlideOut() },
                ) {
                    dashboardScreen()
                }
            },
        )
    }
}