package com.android.core.previews

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
@Preview(
    name = "Small - Dark",
    showBackground = true,
    showSystemUi = true,
    device = Devices.NEXUS_5,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Medium - Dark",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_2,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Full HD - Dark",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7_PRO,
    uiMode = UI_MODE_NIGHT_YES
)
@Preview(
    name = "Small - Light",
    showBackground = true,
    showSystemUi = true,
    device = Devices.NEXUS_5,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Medium - Light",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_2,
    uiMode = UI_MODE_NIGHT_NO
)
@Preview(
    name = "Full HD - Light",
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_7_PRO,
    uiMode = UI_MODE_NIGHT_NO
)
annotation class DeviceCompatPreviews

@DeviceCompatPreviews
@Composable
fun DeviceCompatPreview() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
        content = {
            Button(onClick = { }) {
                Text(text = "Button".uppercase())
            }
        },
    )
}