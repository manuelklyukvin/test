package gradle_plugins

import configs.GradleVersions
import dependencies.androidxCore
import modules.coreDomain
import org.gradle.api.Project
import utils.android

class DataGradlePlugin : CoreGradlePlugin() {

    override fun setPlugins(project: Project) {
        project.apply {
            plugin("android-library")
            plugin("kotlin-android")
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
        }
    }

    override fun setDependencies(project: Project) {
        project.dependencies.apply {
            androidxCore()
            coreDomain()
        }
    }
}