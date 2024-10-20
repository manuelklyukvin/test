package modules

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import utils.implementation

fun DependencyHandler.corePresentation() = implementation(project(":core:presentation"))

fun DependencyHandler.coreDomain() = implementation(project(":core:domain"))

fun DependencyHandler.coreData() = implementation(project(":core:data"))