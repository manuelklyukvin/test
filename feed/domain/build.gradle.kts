import dependencies.serialization
import gradle_plugins.DomainGradlePlugin

apply<DomainGradlePlugin>()

plugins {
    alias(libs.plugins.kotlin.serialization)
}

dependencies {
    serialization()
}