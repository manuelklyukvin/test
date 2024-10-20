package modules

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import utils.implementation

fun DependencyHandler.feedPresentation() = implementation(project(":feed:presentation"))

fun DependencyHandler.feedDomain() = implementation(project(":feed:domain"))

fun DependencyHandler.feedData() = implementation(project(":feed:data"))