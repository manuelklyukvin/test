pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "test"
include(":app")
include(
    ":core",
    ":core:presentation",
    ":core:domain",
    ":core:data"
)

include(":auth")
include(
    ":auth:sign_in",
    ":auth:sign_in:presentation"
)
include(
    ":auth:code",
    ":auth:code:presentation"
)

include(
    ":vacancy",
    ":vacancy:presentation",
    ":vacancy:domain"
)

include(
    ":search",
    ":search:presentation",
    ":search:domain",
    ":search:data"
)

include(
    ":favorite",
    ":favorite:presentation",
    ":favorite:domain"
)