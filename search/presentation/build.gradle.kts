import configs.GradleNamespaces
import gradle_plugins.PresentationGradlePlugin
import modules.searchData
import modules.searchDomain

apply<PresentationGradlePlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

android {
    namespace = GradleNamespaces.SEARCH_PRESENTATION
}

dependencies {
    searchDomain()
    searchData()
}