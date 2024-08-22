import com.android.build.api.dsl.VariantDimension

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kotlin.ksp)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.protobuf)
    alias(libs.plugins.sonarqube)
    alias(libs.plugins.firebase.crashlitycs)
    alias(libs.plugins.kotlin.serialization)
//    alias(libs.plugins.gms.googleServices)
}

android {
    namespace = ConfigData.NAMESPACE
    compileSdk = ConfigData.COMPILE_SDK

    defaultConfig {
        minSdk = ConfigData.MIN_SDK
        targetSdk = ConfigData.TARGET_SDK

        applicationId = ConfigData.APPLICATION_ID

        multiDexEnabled = true

        testInstrumentationRunner = ConfigData.TEST_INSTRUMENTATION_RUNNER
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        create(ConfigData.DEBUG_CONFIG) {
            keyAlias = KeyStoreData.KEY_ALIAS
            keyPassword = KeyStoreData.KEYSTORE_PASSWORD
            storeFile = file(KeyStoreData.KEYSTORE_FILE_PATH)
            storePassword = KeyStoreData.KEYSTORE_PASSWORD
        }
        create(ConfigData.RELEASE_CONFIG) {
            keyAlias = KeyStoreData.KEY_ALIAS
            keyPassword = KeyStoreData.KEYSTORE_PASSWORD
            storeFile = file(KeyStoreData.KEYSTORE_FILE_PATH)
            storePassword = KeyStoreData.KEYSTORE_PASSWORD
        }
    }

    buildTypes {
        getByName(ConfigData.DEBUG) {
            buildConfigBoolean(key = UrlConfig.Names.ENABLE_LOGGER, value = "true")
            buildConfigBoolean(key = UrlConfig.Names.ENABLE_CRASHLYTICS, value = "false")

            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
            isJniDebuggable = false
            signingConfig = signingConfigs.getByName(ConfigData.DEBUG_CONFIG)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName(ConfigData.RELEASE) {
            buildConfigBoolean(key = UrlConfig.Names.ENABLE_LOGGER, value = "false")
            buildConfigBoolean(key = UrlConfig.Names.ENABLE_CRASHLYTICS, value = "true")

            isMinifyEnabled = false
            isDebuggable = true
            isShrinkResources = false
            isJniDebuggable = false
            signingConfig = signingConfigs.getByName(ConfigData.DEBUG_CONFIG)
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }

    flavorDimensions += ConfigData.Flavors.FLAVOR

    productFlavors {
        create(ConfigData.Flavors.DEV) {
            applicationIdSuffix = ConfigData.Flavors.APP_ID_SUFFIX_DEV
            dimension = ConfigData.Flavors.FLAVOR
            versionCode = ConfigData.DevVersion.VERSION_CODE
            versionName = ConfigData.DevVersion.VERSION_NAME
            signingConfig = signingConfigs.getByName(ConfigData.DEBUG_CONFIG)
            buildConfigString(key = UrlConfig.Names.BASE_URL, value = UrlConfig.Dev.BASE_URL)
        }

        create(ConfigData.Flavors.SIT) {
            applicationIdSuffix = ConfigData.Flavors.APP_ID_SUFFIX_SIT
            dimension = ConfigData.Flavors.FLAVOR
            versionCode = ConfigData.SitVersion.VERSION_CODE
            versionName = ConfigData.SitVersion.VERSION_NAME
            signingConfig = signingConfigs.getByName(ConfigData.DEBUG_CONFIG)
            buildConfigString(key = UrlConfig.Names.BASE_URL, value = UrlConfig.Sit.BASE_URL)
        }

        create(ConfigData.Flavors.PROD) {
            dimension = ConfigData.Flavors.FLAVOR
            versionCode = ConfigData.ProdVersion.VERSION_CODE
            versionName = ConfigData.ProdVersion.VERSION_NAME
            signingConfig = signingConfigs.getByName(ConfigData.RELEASE_CONFIG)
            buildConfigString(key = UrlConfig.Names.BASE_URL, value = UrlConfig.Prod.BASE_URL)
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = ConfigData.KOTLIN_VERSION
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = ConfigData.KOTLIN_COMPILER_VERSION
    }

    packaging {
        resources {
            excludes += ConfigData.EXCLUDE_PATH
        }
    }

    lint {
        checkAllWarnings = true
        warningsAsErrors = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.splashscreen)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    testImplementation(libs.junit)

    /******************************************************
     ***** DI - Hilt
     *******************************************************/
    implementation(libs.di.hilt.android)
    ksp(libs.di.hilt.compiler)

    /******************************************************
     ***** Navigation
     *******************************************************/
    implementation(libs.navigation.compose)
    implementation(libs.navigation.hilt.compose)
    implementation(libs.kotlinx.serialization.json)

    /******************************************************
     ***** Retrofit and Moshi
     *******************************************************/
    implementation(libs.retrofit)
    implementation(libs.retrofit.converter.moshi)
    implementation(libs.retrofit.converter.json)
    implementation(libs.retrofit.moshi.kotlin)
    implementation(libs.retrofit.okHttp)

    /******************************************************
     ***** Other libs
     *******************************************************/
    implementation(libs.utils.timber)

    debugImplementation(libs.utils.chuckerDebug)
    releaseImplementation(libs.utils.chuckerRelease)
}

fun VariantDimension.buildConfigString(key: String, value: String) {
    buildConfigField("String", key, value)
}

fun VariantDimension.buildConfigBoolean(key: String, value: String) {
    buildConfigField("Boolean", key, value)
}
