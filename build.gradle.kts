plugins {
    kotlin("multiplatform")
}
allprojects {
    apply(plugin = "org.jetbrains.kotlin.multiplatform")
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
    group = "net.kigawa.leafia"
    version = System.getenv("VERSION") ?: "dev"
    kotlin {
        compilerOptions {
            freeCompilerArgs = listOf("-Xcontext-parameters")
        }
        jvm {
        }
        sourceSets {
            commonMain {}
            commonTest {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
        }
    }
}