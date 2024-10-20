package modules

import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.project
import utils.implementation

fun DependencyHandler.signInPresentation() = implementation(project(":auth:sign_in:presentation"))

fun DependencyHandler.codePresentation() = implementation(project(":auth:code:presentation"))