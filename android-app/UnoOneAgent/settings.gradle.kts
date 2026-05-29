pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        // Optional: add third-party maven repos here if needed for native engine libraries
    }
}

rootProject.name = "UnoOneAgent"

include(
    ":app",
    ":core",
    ":storage",
    ":modelmanager",
    ":localbrain",
    ":voice",
    ":agentrouter",
    ":safetyguard",
    ":phonecontrol",
    ":memory",
    ":skills",
    ":observability",
    ":accessibilitycontrol"
)
