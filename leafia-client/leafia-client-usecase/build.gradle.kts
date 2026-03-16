plugins {
}
kotlin {
    jvm {}
    sourceSets {
        commonMain {
            dependencies {
                api(project(":leafia-client:leafia-client-domain"))
            }
        }
    }
}