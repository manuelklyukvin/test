package gradle_plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

abstract class CoreGradlePlugin : Plugin<Project> {

    override fun apply(project: Project) {
        setPlugins(project)
        setProjectConfig(project)
        setDependencies(project)
    }

    protected abstract fun setPlugins(project: Project)
    protected abstract fun setProjectConfig(project: Project)
    protected abstract fun setDependencies(project: Project)
}