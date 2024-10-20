package dependencies

object Dependencies {

    const val CORE_KTX = "androidx.core:core-ktx:${DependencyVersions.CORE_KTX}"
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${DependencyVersions.LIFECYCLE_RUNTIME_KTX}"
    const val ACTIVITY_COMPOSE = "androidx.activity:activity-compose:${DependencyVersions.ACTIVITY_COMPOSE}"
    const val COMPOSE_BOM = "androidx.compose:compose-bom:${DependencyVersions.COMPOSE_BOM}"
    const val UI = "androidx.compose.ui:ui"
    const val UI_GRAPHICS = "androidx.compose.ui:ui-graphics"
    const val UI_TOOLING = "androidx.compose.ui:ui-tooling"
    const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
    const val MATERIAL = "androidx.compose.material3:material3"

    const val HILT = "com.google.dagger:hilt-android:${DependencyVersions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-android-compiler:${DependencyVersions.HILT}"
    const val HILT_NAVIGATION = "androidx.hilt:hilt-navigation-compose:${DependencyVersions.HILT_NAVIGATION}"

    const val SERIALIZATION = "org.jetbrains.kotlinx:kotlinx-serialization-json:${DependencyVersions.SERIALIZATION}"
    const val GSON = "com.google.code.gson:gson:${DependencyVersions.GSON}"

    const val NAVIGATION_COMPOSE = "androidx.navigation:navigation-compose:${DependencyVersions.NAVIGATION_COMPOSE}"

    const val SPLASH_SCREEN = "androidx.core:core-splashscreen:${DependencyVersions.SPLASH_SCREEN}"
}