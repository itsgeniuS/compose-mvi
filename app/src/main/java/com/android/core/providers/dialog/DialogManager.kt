package com.android.core.providers.dialog

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Created by ThulasiRajan.P on 8/8/2024
 */
object DialogManager {
    private val _dialogEvents = MutableSharedFlow<DialogEvent>(extraBufferCapacity = 1)
    val dialogEvents: SharedFlow<DialogEvent> = _dialogEvents.asSharedFlow()

    fun showSingleActionDialog(message: String, onConfirm: () -> Unit) {
        _dialogEvents.tryEmit(DialogEvent.SingleAction(message, onConfirm))
    }

    fun toggleProgress(canShowLoading: Boolean) {
        _dialogEvents.tryEmit(DialogEvent.ToggleProgress(canShowLoading))
    }

    fun showMultiActionDialog(message: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {
        _dialogEvents.tryEmit(DialogEvent.MultiAction(message, onConfirm, onDismiss))
    }

    fun showInfoDialog(title: String, message: String) {
        _dialogEvents.tryEmit(DialogEvent.Info(title, message))
    }

    fun dismissDialog() {
        _dialogEvents.tryEmit(DialogEvent.Dismiss)
    }
}
