package com.android.data.remote.common

import java.io.IOException

/**
 * Created by ThulasiRajan.P on 9/8/2024
 */
object AppException {
    class NoInternetException(message: String) : Exception(message)
    class UnauthorizedException(message: String) : Exception(message)
    class ServerErrorException(message: String) : Exception(message)

    class CustomException(
        val errorCode: String,
        val title: String,
        override val message: String,
    ) : IOException(message) {

        constructor(
            errorCode: String,
            errorData: String,
        ) : this("Error occurred $errorCode ", errorCode, errorData)

        constructor(errorCode: String) : this("Error occurred with code $errorCode", errorCode)

        constructor(id: Int, title: String, message: String) : this(
            "$id", title = title, message = message
        )
    }
}
