package com.android.core.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
object ImgUtils {

    fun decodeBase64ToBitmap(base64String: String): Bitmap? {
        val imageBytes = Base64.decode(base64String, Base64.DEFAULT)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
}
