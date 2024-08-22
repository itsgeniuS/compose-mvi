package com.android.core.extension

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */

@Stable
fun Modifier.paddingTop(value: Dp) = this.padding(top = value)

@Stable
fun Modifier.paddingBottom(value: Dp) = this.padding(bottom = value)

@Stable
fun Modifier.paddingStart(value: Dp) = this.padding(start = value)

@Stable
fun Modifier.paddingEnd(value: Dp) = this.padding(end = value)

@Stable
fun Modifier.paddingStartEnd(value: Dp) = this.padding(start = value, end = value)

@Stable
fun Modifier.paddingTopBottom(value: Dp) = this.padding(top = value, bottom = value)
