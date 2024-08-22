package com.android.core.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.android.core.navigtation.LocalNavigationManager
import com.android.core.navigtation.NavigationProvider
import com.android.core.navigtation.graphs.AppNavGraph
import com.android.core.providers.LocalSpacingProvider
import com.android.core.providers.LocalStringProvider
import com.android.core.providers.Spacing
import com.android.core.providers.StringProvider
import com.android.core.resource.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            AppTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()

    CompositionLocalProvider(
        LocalNavigationManager provides NavigationProvider(navController),
        LocalStringProvider provides StringProvider(),
        LocalSpacingProvider provides Spacing(),
    ) {
        AppNavGraph(navController)
    }
}
