package com.android.core.utils

import android.icu.text.SimpleDateFormat
import java.text.ParseException
import java.util.Calendar
import java.util.Locale


/**
 * Created by ThulasiRajan.P on 17/2/2024
 */

object DateUtils {
    const val RESPONSE_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss"
    const val RESPONSE_DATE_FORMAT_1 = "yyyy-MM-dd HH:mm"
    const val FORMAT_DAY_OF_WEEK = "EEEE"
    const val FORMAT_MALAY_LOTTERY_DETAILS = "dd MMM yyyy"
    const val LOTTERY_DETAILS_INPUT_FORMAT = "yyyy-MM-dd"
    const val SPORTS_SHOW_DATE_FORMAT = "EE dd"

    fun getCurrentDateForApi(dateFormat: String): String {
        val calendar = Calendar.getInstance()
        val outputFormat = SimpleDateFormat(dateFormat, Locale.US)
        return outputFormat.format(calendar.time)
    }

    @Throws(ParseException::class)
    fun convertDateToTimestamp(dateString: String?): Long {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date = dateFormat.parse(dateString)
        return date.time
    }

    @Throws(ParseException::class)
    fun isPast(dateString1: String?, dateString2: String?): Boolean {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val date1 = dateFormat.parse(dateString1)
        val date2 = dateFormat.parse(dateString2)

        val cal1 = Calendar.getInstance()
        cal1.time = date1
        cal1.timeZone = java.util.TimeZone.getTimeZone("UTC")
        val cal2 = Calendar.getInstance()
        cal2.time = date2
        cal2.timeZone = java.util.TimeZone.getTimeZone("UTC")

        return cal1.before(cal2)
    }

    @Throws(ParseException::class)
    fun isFuture(dateString1: String?, dateString2: String?): Boolean {
        return !isPast(dateString1, dateString2)
    }
}
