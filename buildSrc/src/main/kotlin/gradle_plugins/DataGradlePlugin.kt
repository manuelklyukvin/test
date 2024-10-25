package gradle_plugins

import configs.GradleVersions
import modules.coreData
import modules.coreDomain
import org.gradle.api.Project
import utils.java

class DataGradlePlugin : CoreGradlePlugin() {

    override fun setPlugins(project: Project) {
        project.apply {
            plugin("org.jetbrains.kotlin.jvm")
        }
    }

    override fun setProjectConfig(project: Project) {
        project.java().apply {
            sourceCompatibility = GradleVersions.JAVA
            targetCompatibility = GradleVersions.JAVA
        }
    }

    override fun setDependencies(project: Project) {
        project.dependencies.apply {
            coreData()
            coreDomain()
        }
    }
}