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

include(
    ":auth",
    ":auth:sign_in",
    ":auth:code",
)
include(":auth:sign_in:presentation")
include(":auth:code:presentation")

include(
    ":feed",
    ":feed:presentation",
    ":feed:domain",
    ":feed:data"
)