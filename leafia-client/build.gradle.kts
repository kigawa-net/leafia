@file:OptIn(ExperimentalKotlinGradlePluginApi::class)

import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi

plugins {
}
kotlin {
    jvm {
        binaries {
            executable {
                mainClass = "net.kigawa.leafia.client.Main"
            }
        }
    }
    sourceSets {
        commonMain {
            dependencies {
                api(project(":leafia-client:leafia-client-usecase"))
            }
        }
    }
}