plugins {
    kotlin("multiplatform")
}
kotlin {
    jvm {}
    sourceSets {
        commonMain {
            dependencies {
                api("net.kigawa.kodel:core:4.0.6-dev")
            }
        }
    }
}