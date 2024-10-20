package gradle_plugins

import configs.GradleVersions
import dependencies.androidx
import dependencies.hilt
import modules.coreDomain
import modules.corePresentation
import org.gradle.api.Project
import utils.android

class PresentationGradlePlugin : CoreGradlePlugin() {

    override fun setPlugins(project: Project) {
        project.apply {
            plugin("android-library")
            plugin("kotlin-android")
            plugin("org.jetbrains.kotlin.plugin.compose")
            plugin("com.google.devtools.ksp")
            plugin("com.google.dagger.hilt.android")
        }
    }

    override fun setProjectConfig(project: Project) {
        project.android().apply {
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
    }

    override fun setDependencies(project: Project) {
        project.dependencies.apply {
            androidx()
            hilt()

            corePresentation()
            coreDomain()
        }
    }
}