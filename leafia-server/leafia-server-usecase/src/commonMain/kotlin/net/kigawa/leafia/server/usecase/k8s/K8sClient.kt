package net.kigawa.leafia.server.usecase.k8s

import net.kigawa.leafia.server.domain.LeafiaConfig
import net.kigawa.leafia.server.domain.ProjectId

interface K8sClient {
    fun searchOrCreateCachePvc(projectId: ProjectId)
    fun lockProject(projectId: ProjectId)
    fun unlockProject(projectId: ProjectId)
    fun createJob(projectId: ProjectId, config: LeafiaConfig)
}