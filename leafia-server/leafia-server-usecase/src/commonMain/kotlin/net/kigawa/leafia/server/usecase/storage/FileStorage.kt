package net.kigawa.leafia.server.usecase.storage

import net.kigawa.leafia.server.domain.ProjectId

interface FileStorage {
    fun getFileHashes(projectId: ProjectId): Map<String, String>
    fun applyFileDiff(projectId: ProjectId, files: Map<String, ByteArray>)
    fun validateFileList(projectId: ProjectId, fileList: List<String>)
}