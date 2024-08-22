package com.android.core.navigtation.navTypes

import android.net.Uri
import android.os.Bundle
import androidx.navigation.NavType
import com.android.presentation.login.navigation.LoginArgs
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Created by ThulasiRajan.P on 12/8/2024
 */
object LoginArgsType {
    val LoginArgsType = object : NavType<LoginArgs>(
        isNullableAllowed = false
    ) {
        override fun get(bundle: Bundle, key: String): LoginArgs? {
            return Json.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): LoginArgs {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: LoginArgs): String {
            return Uri.encode(Json.encodeToString(value))
        }

        override fun put(bundle: Bundle, key: String, value: LoginArgs) {
            bundle.putString(key, Json.encodeToString(value))
        }
    }
}
