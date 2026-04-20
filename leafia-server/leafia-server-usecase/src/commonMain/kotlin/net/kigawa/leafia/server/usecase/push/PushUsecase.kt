package net.kigawa.leafia.server.usecase.push

import net.kigawa.leafia.server.domain.LeafiaConfig
import net.kigawa.leafia.server.domain.ProjectId
import net.kigawa.leafia.server.usecase.k8s.K8sClient
import net.kigawa.leafia.server.usecase.storage.FileStorage

class PushUsecase(
    private val k8sClient: K8sClient,
    private val fileStorage: FileStorage,
) {
    fun start(projectId: ProjectId, config: LeafiaConfig) {
        k8sClient.searchOrCreateCachePvc(projectId)
        k8sClient.lockProject(projectId)
    }

    fun compareFileHashes(projectId: ProjectId, clientHashes: Map<String, String>): List<String> {
        val serverHashes = fileStorage.getFileHashes(projectId)
        return clientHashes.entries
            .filter { (path, hash) -> serverHashes[path] != hash }
            .map { it.key } +
            serverHashes.keys.filter { it !in clientHashes }
    }

    fun applyFiles(
        projectId: ProjectId,
        modifiedFiles: Map<String, ByteArray>,
        fileList: List<String>,
    ) {
        fileStorage.validateFileList(projectId, fileList)
        fileStorage.applyFileDiff(projectId, modifiedFiles)
    }

    fun createJob(projectId: ProjectId, config: LeafiaConfig) {
        k8sClient.createJob(projectId, config)
    }
}