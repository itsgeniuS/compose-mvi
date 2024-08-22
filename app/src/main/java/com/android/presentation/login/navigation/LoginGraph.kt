package com.android.presentation.login.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.android.core.navigtation.navTypes.LoginArgsType
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf


/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
internal const val LOGIN = "login"
const val LOGIN_WITH_ARGS = "$LOGIN/{args}"

@Serializable
data class LoginScreen(
    val fromScreen: String,
)

internal fun NavGraphBuilder.loginScreen() {

//    composable<LoginScreen> {
//
//    }

    composable<LoginScreen>(
        typeMap = mapOf(
            typeOf<LoginArgs>() to LoginArgsType.LoginArgsType,
        )
    ) {
        val arguments = it.toRoute<LoginScreen>()
        LoginScreen(fromScreen = arguments.fromScreen)
    }

//    composable(
//        route = LOGIN_WITH_ARGS,
//        arguments = listOf(navArgument(name = "args") { type = NavType.StringType }),
//    ) { navBack ->
//        navBack.arguments?.getString("args")?.let { _ ->
//            LoginContract {
//
//            }
//        }
//    }
}

@Serializable
data class LoginArgs(
    val type: String,
)
