plugins {
}
kotlin {
    jvm {}
    sourceSets {
        commonMain {
            dependencies {
                api(project(":leafia-server:leafia-server-domain"))
            }
        }
    }
}