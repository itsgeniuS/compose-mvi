package com.android.core.navigtation

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

/**
 * Created by ThulasiRajan.P on 7/8/2024
 */
class NavigationProvider(
    private val navController: NavController,
) {
    fun navigate(route: String) {
        navController.navigate(route)
    }

    fun goBack() {
        navController.popBackStack()
    }

    fun navigateWithData(route: String, data: String) {
        navController.navigate(route.plus("/").plus(data))
    }

    fun switchBottomMenu(route: String) {
        navController.navigate(route) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }
}

val LocalNavigationManager = staticCompositionLocalOf<NavigationProvider> {
    error("No NavigationManager provided")
}