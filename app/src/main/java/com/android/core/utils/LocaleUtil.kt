package com.android.core.utils

import android.content.res.Resources
import androidx.core.os.ConfigurationCompat
import java.util.Locale

/**
 * Created by ThulasiRajan.P on 29/3/2024
 */
object LocaleUtil {
    const val DEFAULT_LANGUAGE = "en"

    //system level language keys
    private const val SYSTEM_LANGUAGE_CHINESE = "zh"
    private const val SYSTEM_LANGUAGE_KHMER = "kh"

    //API level language keys
    const val API_KEY_CHINESE = "zh_CN"
    private const val API_KEY_KHMER = "km"

    private val supportedLocales =
        arrayListOf(DEFAULT_LANGUAGE, API_KEY_CHINESE, API_KEY_KHMER)

    //to get the device system language
    private fun getDeviceLanguage(): String {
        return ConfigurationCompat.getLocales(
            Resources.getSystem().configuration
        )[0]?.language ?: DEFAULT_LANGUAGE
    }

    //this method will check the system locale and api locale
    //if the system locale is in supported languages then the key will be returned
    //else the default language (i.e) english will be returned
    fun getPreferredLocale(): String {
        val systemLanguage = getDeviceLanguage()
        val chosenLanguage = "AppPreferences.getSelectedLanguages()"
        val preferredLocale = chosenLanguage.ifEmpty {
            return when {
                systemLanguage.equals(SYSTEM_LANGUAGE_CHINESE, ignoreCase = true) -> API_KEY_CHINESE
                systemLanguage.equals(SYSTEM_LANGUAGE_KHMER, ignoreCase = true) -> API_KEY_KHMER
                else -> systemLanguage
            }
        }
        return if (supportedLocales.contains(preferredLocale)) {
            preferredLocale
        } else {
            DEFAULT_LANGUAGE
        }
    }

    fun getLocaleForSelectedLanguage(): Locale {
        return when ("AppPreferences.getSelectedLanguages()") {
            API_KEY_CHINESE -> Locale("zh")
            API_KEY_KHMER -> Locale("km")
            else -> Locale(DEFAULT_LANGUAGE)
        }
    }
}
