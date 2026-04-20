package net.kigawa.leafia.server.domain

data class ProjectState(val raw: ByteArray) {
    override fun equals(other: Any?) = other is ProjectState && raw.contentEquals(other.raw)
    override fun hashCode() = raw.contentHashCode()
}