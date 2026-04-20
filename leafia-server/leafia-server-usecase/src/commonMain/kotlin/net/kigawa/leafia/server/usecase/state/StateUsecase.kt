package net.kigawa.leafia.server.usecase.state

import net.kigawa.leafia.server.domain.ProjectId
import net.kigawa.leafia.server.domain.ProjectState
import net.kigawa.leafia.server.usecase.k8s.K8sClient
import net.kigawa.leafia.server.usecase.storage.StateStorage

class StateUsecase(
    private val k8sClient: K8sClient,
    private val stateStorage: StateStorage,
) {
    fun loadState(projectId: ProjectId): ProjectState {
        return stateStorage.loadState(projectId) ?: ProjectState(ByteArray(0))
    }

    fun saveState(projectId: ProjectId, state: ProjectState) {
        stateStorage.saveState(projectId, state)
    }

    fun endJob(projectId: ProjectId) {
        k8sClient.unlockProject(projectId)
    }
}