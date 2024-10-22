import configs.GradleNamespaces
import dependencies.gson
import dependencies.serialization
import gradle_plugins.DataGradlePlugin
import modules.searchDomain

apply<DataGradlePlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

android {
    namespace = GradleNamespaces.SEARCH_DATA
}

dependencies {
    serialization()
    gson()

    searchDomain()
}