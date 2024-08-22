package com.android.presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.android.core.extension.paddingStartEnd
import com.android.core.extension.paddingTopBottom
import com.android.core.previews.ThemedPreviews
import com.android.core.resource.AppDimens
import com.kkCasino.R

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Composable
fun SplashScreen(
    onAction: (SplashAction) -> Unit,
) {

    LaunchedEffect(
        key1 = Unit,
        block = {
            onAction(SplashAction.CallApi)
        }
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paddingTopBottom(AppDimens.paddingMedium)
            .paddingStartEnd(AppDimens.paddingMedium),
        contentAlignment = Alignment.Center,
        content = {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "ic_launcher_foreground",
                modifier = Modifier.height(AppDimens.splashLogoSize)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_background),
                contentDescription = "ic_launcher_foreground",
                modifier = Modifier.height(AppDimens.splashLogoSize)
            )
        }
    )
}

@ThemedPreviews
@Composable
fun SplashScreenPreview() {
    SplashScreen(onAction = {})
}