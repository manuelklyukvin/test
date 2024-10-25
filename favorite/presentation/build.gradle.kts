import configs.GradleNamespaces
import gradle_plugins.PresentationGradlePlugin
import modules.favoriteDomain

apply<PresentationGradlePlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

android {
    namespace = GradleNamespaces.FAVORITE
}

dependencies {
    favoriteDomain()
}