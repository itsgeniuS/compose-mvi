package com.android.core.providers.dialog

/**
 * Created by ThulasiRajan.P on 8/8/2024
 */
sealed class DialogEvent {
    data class SingleAction(
        val message: String,
        val onConfirm: () -> Unit,
    ) : DialogEvent()

    data class MultiAction(
        val message: String,
        val onConfirm: () -> Unit,
        val onDismiss: () -> Unit,
    ) : DialogEvent()

    data class Info(
        val title: String,
        val message: String,
    ) : DialogEvent()

    data class ToggleProgress(
        val canShowProgress: Boolean
    ) : DialogEvent()

    data object Dismiss : DialogEvent()
}
