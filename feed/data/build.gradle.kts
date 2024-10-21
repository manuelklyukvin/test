import configs.GradleNamespaces
import dependencies.gson
import dependencies.serialization
import gradle_plugins.DataGradlePlugin
import modules.feedDomain

apply<DataGradlePlugin>()

plugins {
    `android-library`
    `kotlin-android`
}

android {
    namespace = GradleNamespaces.FEED_DATA
}

dependencies {
    serialization()
    gson()

    feedDomain()
}