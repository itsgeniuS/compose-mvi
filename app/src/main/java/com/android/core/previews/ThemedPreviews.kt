package com.android.core.previews

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.ui.tooling.preview.Preview

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Preview(name = "Dark Mode", showBackground = true, uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Preview(name = "Light Mode", showBackground = true, uiMode = UI_MODE_NIGHT_NO, showSystemUi = true)
annotation class ThemedPreviews
