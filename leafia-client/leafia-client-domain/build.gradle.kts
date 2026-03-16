plugins {
}
kotlin {
    jvm {}
    sourceSets {
        commonMain {
            dependencies {
                api("net.kigawa.kodel:kodel-domain:${Version.KODEL}")
            }
        }
    }
}