/**
 * Created by ThulasiRajan.P on 1/8/2024
 */
object UrlConfig {

    interface Names {
        companion object {
            const val BASE_URL = "BASE_URL"
            const val ENABLE_LOGGER = "ENABLE_LOGGER"
            const val ENABLE_CRASHLYTICS = "ENABLE_CRASHLYTICS"
        }
    }

    interface Dev {
        companion object {
            const val BASE_URL = "\"https://itsgenius.github.com\""
        }
    }

    interface Sit {
        companion object {
            const val BASE_URL = "\"https://itsgenius.github.com\""
        }
    }

    interface Prod {
        companion object {
            const val BASE_URL = "\"https://itsgenius.github.com\""
        }
    }
}
