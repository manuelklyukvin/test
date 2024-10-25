import configs.GradleVersions
import dependencies.gson
import modules.coreDomain

plugins {
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = GradleVersions.JAVA
    targetCompatibility = GradleVersions.JAVA
}

dependencies {
    gson()
    coreDomain()
}