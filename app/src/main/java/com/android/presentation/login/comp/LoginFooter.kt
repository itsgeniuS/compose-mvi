package com.android.presentation.login.comp

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.android.core.resource.AppIcons

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Composable
fun LoginFooter() {
    Image(
        painter = painterResource(id = AppIcons.launcherBackGround),
        contentDescription = "launcherBackGround",
        modifier = Modifier
//            .height(50.dp).width(100.dp)
            .size(100.dp)
    )
}

@Preview
@Composable
fun LFPREview() {
    LoginFooter()
}