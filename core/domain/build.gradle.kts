import configs.GradleVersions

plugins {
    id("org.jetbrains.kotlin.jvm")
}

java {
    sourceCompatibility = GradleVersions.JAVA
    targetCompatibility = GradleVersions.JAVA
}