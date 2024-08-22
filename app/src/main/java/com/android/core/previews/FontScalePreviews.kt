package com.android.core.previews

import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Preview(name = "Small Font Size", fontScale = 0.75f,)
@Preview(name = "Default Font Size", fontScale = 1f, device = Devices.FOLDABLE)
@Preview(name = "Large Font Size", fontScale = 1.5f, device = Devices.FOLDABLE)
annotation class FontScalePreviews
