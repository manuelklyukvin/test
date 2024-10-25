package dependencies

import org.gradle.api.artifacts.dsl.DependencyHandler
import utils.implementation
import utils.ksp

fun DependencyHandler.androidx() {
    implementation(Dependencies.CORE_KTX)
    implementation(Dependencies.LIFECYCLE_RUNTIME_KTX)
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(platform(Dependencies.COMPOSE_BOM))
    implementation(Dependencies.UI)
    implementation(Dependencies.UI_GRAPHICS)
    implementation(Dependencies.UI_TOOLING)
    implementation(Dependencies.UI_TOOLING_PREVIEW)
    implementation(Dependencies.MATERIAL)
}

fun DependencyHandler.hilt() {
    implementation(Dependencies.HILT)
    ksp(Dependencies.HILT_COMPILER)
}

fun DependencyHandler.hiltNavigation() {
    implementation(Dependencies.HILT_NAVIGATION)
}

fun DependencyHandler.serialization() {
    implementation(Dependencies.SERIALIZATION)
}

fun DependencyHandler.gson() {
    implementation(Dependencies.GSON)
}

fun DependencyHandler.navigation() {
    implementation(Dependencies.NAVIGATION_COMPOSE)
    serialization()
}

fun DependencyHandler.splashScreen() {
    implementation(Dependencies.SPLASH_SCREEN)
}