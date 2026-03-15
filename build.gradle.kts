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
        js {
            browser {
                testTask {
                }
            }
            nodejs {
                testTask {
                }
            }
        }
        listOf(
            iosX64(),
            iosArm64(),
            iosSimulatorArm64()
        ).forEach { _ ->
        }
        sourceSets {
            commonMain {}
            commonTest {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
            jsTest {
                dependencies {
                    implementation(kotlin("test-js"))
                }
            }
        }
    }
}