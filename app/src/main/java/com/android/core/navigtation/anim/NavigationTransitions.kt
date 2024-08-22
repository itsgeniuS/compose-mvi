package com.android.core.navigtation.anim

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.navigation.NavBackStackEntry

/**
 * Created by ThulasiRajan.P on 7/8/2024
 */
fun AnimatedContentTransitionScope<NavBackStackEntry>.screenSlideIn(): EnterTransition =
    slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start)

fun screenFadeOut(): ExitTransition = fadeOut()

fun screenFadeIn(): EnterTransition = fadeIn()

fun AnimatedContentTransitionScope<NavBackStackEntry>.screenSlideOut(): ExitTransition =
    slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End)
