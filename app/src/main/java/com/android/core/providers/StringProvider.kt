package com.android.core.providers

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocal
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import java.util.Locale

/**
 * Created by ThulasiRajan.P on 5/8/2024
 */
/**
 * Created by ThulasiRajan.P on 3/8/2024
 *
 * CompositionLocal is a tool for passing data down through the Composition implicitly.
 * This StringProvider is used to pass down translations to the child nodes when
 * wrapped with this provider.
 * This provider has to be used before rendering the content on the UI
 *
 * */
class StringProvider {

    companion object {
        private const val PREFIX_KEY = "mobile_"
        private var strings = mutableMapOf<String, String>()

        /**
         * this static function can be used from any viewmodel to update the translations string
         * @translations will be the map of key, values we receive from the API
         * @onTranslationsUpdate just a simple call back in case of synchronization requirement
         * */
        fun setLabels(
            translations: HashMap<String, String>,
            onTranslationsUpdate: () -> Unit = {},
        ) {
            strings.clear()
            strings.putAll(translations)
            onTranslationsUpdate.invoke()
        }
    }

    /**
     * this function can be used from any composable to update the translations string
     * @translations will be the map of key, values we receive from the API
     * @onTranslationsUpdate just a simple call back in case of synchronization requirement
     **/
    fun setLabels(
        translations: HashMap<String, String>,
        onTranslationsUpdate: () -> Unit = {},
    ) {
        strings.clear()
        strings.putAll(translations)
        onTranslationsUpdate.invoke()
    }

    /**
     * this function will return the translation values if the key is present
     * if the key is not present then it will return the auto capitalized sentence from the key itself
     * @key will be the key to be translated to current selected locale
     **/
    fun getString(key: String): String {
        val resultString = strings.getOrDefault(
            "$PREFIX_KEY$key",
            key.replace("_", " ")
        )

        return resultString.ifEmpty {
            key.replace(
                "_", " "
            ).replaceFirstChar {
                if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString()
            }
        }
    }
}

/**
 * CompositionLocal is a tool for passing data down through the Composition implicitly.
 * This provider will reduce the direct dependency on the storage related things.
 * This instance is scoped to a part of the Composition so you can provide
 * different values at different levels of the tree.
 **/
val LocalStringProvider = compositionLocalOf { StringProvider() }

/**
 * This variable provides a single source of truth to the composable at higher level of the node
 * This is used to access the functions and variables declared inside the [CompositionLocal]
 * eg. Text(text = strings.getString("key_of_string))
 **/
val strings: StringProvider
    @Composable
    @ReadOnlyComposable
    get() = LocalStringProvider.current