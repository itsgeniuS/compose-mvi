package com.android.presentation.dashboard.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navDeepLink
import com.android.presentation.dashboard.DashboardContract

/**
 * Created by ThulasiRajan.P on 8/8/2024
 */
const val dashboard = "dashboard"

fun NavGraphBuilder.dashboardScreen() {
    composable(
        dashboard,
       /* deepLinks = listOf(
            navDeepLink { uriPattern = "bottom_bar_routes/dashboard" }
        ),*/
    ) {
        DashboardContract()
    }
}