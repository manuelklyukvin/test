package modules

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import utils.implementation

fun DependencyHandler.favoritePresentation() = implementation(project(":favorite:presentation"))

fun DependencyHandler.favoriteDomain() = implementation(project(":favorite:domain"))