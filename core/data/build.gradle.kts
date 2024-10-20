import configs.GradleNamespaces
import configs.GradleVersions
import dependencies.androidxCore
import dependencies.gson
import modules.coreDomain

plugins {
    `android-library`
    `kotlin-android`
}

android {
    namespace = GradleNamespaces.CORE_DATA
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
}

dependencies {
    androidxCore()
    coreDomain()

    gson()
}