package net.kigawa.leafia.client.domain

import java.nio.file.Path

class FilePath(
    private val dirNames: List<String>,
    private val isStatic: Boolean,
) {
    constructor(strPath: String): this(
        strPath.split("/").filter { it.isNotBlank() }, strPath.startsWith("/")
    )
    val strPath: String get() = dirNames.joinToString("/", prefix = if (isStatic) "/" else "")
    val jvmPath: Path get() = Path.of(strPath)
}