/**
 * Created by Thulasi Rajan P on 30/04/24.
 */
object ConfigData {
    const val COMPILE_SDK = 34
    const val MIN_SDK = 25
    const val TARGET_SDK = 34

    //IF THE NAME SPACE IS UPDATED, THEN PACKAGE NAME SHOULD ALSO NEEDS TO BE UPDATED
    //OF DEX ERROR WE HAVE TO FIX IN MANIFEST
    const val NAMESPACE = "com.kkCasino"

    const val EXCLUDE_PATH = "/META-INF/{AL2.0,LGPL2.1}"
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    const val DEBUG_CONFIG = "debugConfig"
    const val RELEASE_CONFIG = "releaseConfig"
    const val DEBUG = "debug"
    const val RELEASE = "release"

    const val KOTLIN_VERSION = "17"
    const val KOTLIN_COMPILER_VERSION = "1.5.12"

    const val APPLICATION_ID = "com.genius.android"

    interface Flavors {
        companion object {
            const val FLAVOR = "flavor"
            const val APP_ID_SUFFIX_DEV = ".dev"
            const val APP_ID_SUFFIX_SIT = ".sit"
            const val DEV = "dev"
            const val SIT = "sit"
            const val PROD = "prod"
        }
    }

    interface DevVersion {
        companion object {
            private const val MAJOR = 0
            private const val MINOR = 0
            private const val PATCH = 1

            val VERSION_CODE: Int
                get() = getVersionCode(MAJOR, MINOR, PATCH)

            val VERSION_NAME: String
                get() = getVersionName(MAJOR, MINOR, PATCH)
        }
    }

    interface SitVersion {
        companion object {
            private const val MAJOR = 0
            private const val MINOR = 0
            private const val PATCH = 1

            val VERSION_CODE: Int
                get() = getVersionCode(MAJOR, MINOR, PATCH)

            val VERSION_NAME: String
                get() = getVersionName(MAJOR, MINOR, PATCH)
        }
    }

    interface ProdVersion {
        companion object {
            private const val MAJOR = 0
            private const val MINOR = 0
            private const val PATCH = 1

            val VERSION_CODE: Int
                get() = getVersionCode(MAJOR, MINOR, PATCH)

            val VERSION_NAME: String
                get() = getVersionName(MAJOR, MINOR, PATCH)
        }
    }

    fun getVersionCode(major: Int, minor: Int, patch: Int) = major * 10000 + minor * 100 + patch

    fun getVersionName(major: Int, minor: Int, patch: Int) =
        (major * 10000 + minor * 100 + patch).toString()
}
