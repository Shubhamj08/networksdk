pluginManagement {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
//        maven { setUrl("https://jitpack.io") }
    }
}

rootProject.name = "NetworkSDK"
include(":app")
include(":networksdk")
