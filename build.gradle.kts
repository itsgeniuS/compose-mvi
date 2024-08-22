// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.kotlin.parcelize) apply false
    alias(libs.plugins.kotlin.ksp) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.protobuf) apply false
    alias(libs.plugins.sonarqube)
//    id("com.google.gms.google-services") version "4.4.2"
}

//sonar {
//    properties {
//        property("sonar.projectKey", "")
//        property("sonar.projectName", "")
//        property("sonar.host.url", "https://lomasq.com/")
//        property("sonar.login", "thulasirajan")
//        property("sonar.password", "Loma@technology")
//    }
//}
