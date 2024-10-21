import configs.GradleNamespaces
import dependencies.navigation
import gradle_plugins.PresentationGradlePlugin

apply<PresentationGradlePlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

android {
    namespace = GradleNamespaces.VACANCY_PRESENTATION
}

dependencies {
    navigation()
}