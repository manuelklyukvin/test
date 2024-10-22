package modules

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import utils.implementation

fun DependencyHandler.searchPresentation() = implementation(project(":search:presentation"))

fun DependencyHandler.searchDomain() = implementation(project(":search:domain"))

fun DependencyHandler.searchData() = implementation(project(":search:data"))