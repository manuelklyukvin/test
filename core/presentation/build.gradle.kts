import configs.GradleNamespaces
import configs.GradleVersions
import dependencies.androidx
import dependencies.hilt
import dependencies.navigation
import modules.coreData
import modules.coreDomain

plugins {
    `android-library`
    `kotlin-android`
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = GradleNamespaces.CORE_PRESENTATION
    compileSdk = GradleVersions.COMPILE_SDK

    defaultConfig {
        minSdk = GradleVersions.MIN_SDK
    }
    buildTypes {
        release {
            isMinifyEnabled = true
        }
    }
    compileOptions {
        sourceCompatibility = GradleVersions.JAVA
        targetCompatibility = GradleVersions.JAVA
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    androidx()
    hilt()
    navigation()

    coreDomain()
    coreData()
}