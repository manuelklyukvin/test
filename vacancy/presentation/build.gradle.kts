import configs.GradleNamespaces
import dependencies.navigation
import gradle_plugins.PresentationGradlePlugin
import modules.vacancyDomain

apply<PresentationGradlePlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

android {
    namespace = GradleNamespaces.VACANCY
}

dependencies {
    navigation()

    vacancyDomain()
}