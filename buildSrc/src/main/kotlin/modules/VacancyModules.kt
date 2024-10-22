package modules

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import utils.implementation

fun DependencyHandler.vacancyPresentation() = implementation(project(":vacancy:presentation"))

fun DependencyHandler.vacancyDomain() = implementation(project(":vacancy:domain"))