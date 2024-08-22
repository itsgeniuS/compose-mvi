package com.android.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import com.android.core.extension.paddingStartEnd
import com.android.core.extension.paddingTop
import com.android.core.extension.paddingTopBottom
import com.android.core.previews.ThemedPreviews
import com.android.core.resource.AppDimens
import com.android.presentation.login.helpers.MockData

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
@Composable
fun LoginScreen(
    onAction: (LoginAction) -> Unit,
) {
    val email by remember { mutableStateOf(TextFieldValue()) }
    val password by remember { mutableStateOf(TextFieldValue()) }

    BuildList()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .paddingTopBottom(AppDimens.paddingMedium)
            .paddingStartEnd(AppDimens.paddingMedium),
        contentAlignment = Alignment.Center,
        content = {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
            ) {
                Text(
                    text = "Login to continue...",
                    modifier = Modifier.paddingTop(AppDimens.paddingMedium),
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W900,
                    ),
                )

                TextField(
                    modifier = Modifier.paddingTop(AppDimens.spaceLarge),
                    value = email,
                    onValueChange = { value ->
                        if (value.text.isNotEmpty()) {
                            onAction.invoke(LoginAction.OnEnterEmail(value.text))
                        }
                    },
                    label = {
                        Text(
                            text = "Email",
                            style = TextStyle(fontSize = 14.sp),
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        capitalization = KeyboardCapitalization.None,
                    ),
                )

                TextField(
                    modifier = Modifier.paddingTop(AppDimens.paddingLargeLoginScreen),
                    value = password,
                    onValueChange = { value ->
                        if (value.text.isNotEmpty()) {
                            onAction.invoke(LoginAction.OnEnterPassword(value.text))
                        }
                    },
                    label = {
                        Text(
                            text = "Password",
                            style = TextStyle(fontSize = 14.sp),
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email,
                        capitalization = KeyboardCapitalization.None,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                )

                Button(
                    modifier = Modifier.paddingTop(AppDimens.spaceLarge),
                    onClick = {
                        onAction.invoke(LoginAction.OnSubmit)
                    },
                    content = {
                        Text(
                            text = "Login",
                            style = TextStyle(
                                fontSize = 16.sp,
                            ),
                        )
                    }
                )
            }
        }
    )
}

@Composable
fun BuildList() {
    val list by remember { mutableStateOf(MockData.listOfItemSupport)  }
    Column {

    }
}

@ThemedPreviews
@Composable
fun LoginPreview() {
    LoginScreen(onAction = {})
}
