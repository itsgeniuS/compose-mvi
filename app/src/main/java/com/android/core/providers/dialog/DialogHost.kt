package com.android.core.providers.dialog

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.View
import android.view.Window
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import kotlinx.coroutines.launch

/**
 * Created by ThulasiRajan.P on 8/8/2024
 */
@Composable
fun DialogHost() {
    val dialogEvent by DialogManager.dialogEvents.collectAsState(initial = null)
    val scope = rememberCoroutineScope()

    dialogEvent?.let { event ->
        when (event) {
            is DialogEvent.ToggleProgress -> AppProgressDialog(event.canShowProgress)

            is DialogEvent.SingleAction -> SingleActionDialog(
                message = event.message,
                onConfirm = {
                    event.onConfirm()
                    DialogManager.dismissDialog()
                }
            )

            is DialogEvent.MultiAction -> MultiActionDialog(
                message = event.message,
                onConfirm = {
                    event.onConfirm()
                    DialogManager.dismissDialog()
                },
                onDismiss = {
                    event.onDismiss()
                    DialogManager.dismissDialog()
                }
            )

            is DialogEvent.Info -> {
                InfoDialog(
                    message = event.message,
                    onDismiss = {
                        DialogManager.dismissDialog()
                    }
                )
                // Auto-dismiss after 2 seconds
                LaunchedEffect(Unit) {
                    scope.launch {
                        kotlinx.coroutines.delay(2000)
                        DialogManager.dismissDialog()
                    }
                }
            }

            is DialogEvent.Dismiss -> Unit
        }
    }
}

@Composable
fun SingleActionDialog(message: String, onConfirm: () -> Unit) {
    AlertDialog(
        onDismissRequest = { onConfirm() },
        title = { Text(text = "Single Action") },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("OK")
            }
        }
    )
}

@Composable
fun MultiActionDialog(message: String, onConfirm: () -> Unit, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Multi Action") },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onConfirm) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun InfoDialog(message: String, onDismiss: () -> Unit) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(text = "Info") },
        text = { Text(text = message) },
        confirmButton = {
            Button(onClick = onDismiss) {
                Text("OK")
            }
        }
    )
}

@Composable
fun AppProgressDialog(
    showDialog: Boolean,
) {
    if (showDialog) {
        Dialog(
            onDismissRequest = { },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            val curView = LocalView.current
            LaunchedEffect(curView) {
                tailrec fun Context.findWindow(): Window? = when (this) {
                    is Activity -> window
                    is ContextWrapper -> baseContext.findWindow()
                    else -> null
                }

                fun View.findWindow(): Window? =
                    (parent as? DialogWindowProvider)?.window ?: context.findWindow()

                try {
                    val window = curView.findWindow() ?: return@LaunchedEffect
                    val lp = window.attributes
                    lp.dimAmount = 0.3F
                    window.attributes = lp
                } catch (e: Throwable) {
                    e.printStackTrace()
                }
            }

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxSize()
                    .background(White),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(25.dp),
                    color = Color.Green.copy(alpha = 0.65f),
                    strokeWidth = 3.dp,
                    strokeCap = StrokeCap.Round,
                    trackColor = Color.Gray.copy(alpha = 0.3f),
                )
            }
        }
    }
}
