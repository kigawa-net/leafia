plugins {
}
repositories {
    maven("https://jitpack.io")
}
kotlin {
    jvm {}
    sourceSets {
        commonMain {
            dependencies {
//                api("com.github.kigawa-net:kodel:${Version.kodel}")
//                api("com.github.kigawa-net.kodel:kodel-domain:${Version.kodel}")
                api("net.kigawa.kodel:kodel-domain:${Version.KODEL}")
            }
        }
    }
}