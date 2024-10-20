import configs.GradleNamespaces
import gradle_plugins.PresentationGradlePlugin

apply<PresentationGradlePlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

android {
    namespace = GradleNamespaces.SIGN_IN_PRESENTATION
}