rootProject.name = "leafia"
fun Settings.includesIfExists(dir: String, vararg includes: String) {
    if (file(dir).exists()) this.include(*includes)
}

@DslMarker
annotation class IncludeDslMarker

@IncludeDslMarker
class IncludeDsl(
    private val prefix: List<String>,
    private val settings: Settings,
) {
    fun includeIfExists(vararg name: String): Boolean = (prefix + name).let { pathName ->
        var name: String? = null
        val path = pathName.map {
            name = if (name == null) it
            else "$name-$it"
            name
        }
        if (file(path.joinToString(separator = "/")).exists()) {
            settings.include(path.joinToString(separator = ":"))
            true
        } else false
    }

    fun group(name: String, block: IncludeDsl.() -> Unit) = IncludeDsl(prefix + name, settings).block()
    fun includeIfExistsAndGroup(name: String, block: IncludeDsl.() -> Unit) =
        if (includeIfExists(name)) group(name, block)
        else Unit
}

fun Settings.includeDsl(block: IncludeDsl.() -> Unit) {
    IncludeDsl(emptyList(), this).block()
}


includeDsl {
    includeIfExistsAndGroup("leafia-prot") {
        includeIfExists("domain")
        includeIfExists("usecase")
    }
    includeIfExistsAndGroup("leafia-server") {
        includeIfExists("domain")
        includeIfExists("usecase")
    }
    includeIfExistsAndGroup("leafia-client") {
        includeIfExists("domain")
        includeIfExists("usecase")
    }
}


sourceControl {
    gitRepository(uri("https://github.com/kigawa-net/kodel.git")) {
        producesModule("net.kigawa.kodel:core")
        producesModule("net.kigawa.kodel:api")
        producesModule("net.kigawa.kodel:coroutine")
        producesModule("net.kigawa.kodel:kutil")
    }
}