import configs.GradleNamespaces
import configs.GradleVersions
import dependencies.androidx
import dependencies.hilt
import dependencies.splashScreen
import modules.codePresentation
import modules.corePresentation
import modules.feedPresentation
import modules.signInPresentation

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.hilt)
}

android {
    namespace = GradleNamespaces.APP
    compileSdk = GradleVersions.COMPILE_SDK

    defaultConfig {
        applicationId = GradleNamespaces.APP
        minSdk = GradleVersions.MIN_SDK
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
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
    splashScreen()

    corePresentation()

    signInPresentation()
    codePresentation()

    feedPresentation()
}