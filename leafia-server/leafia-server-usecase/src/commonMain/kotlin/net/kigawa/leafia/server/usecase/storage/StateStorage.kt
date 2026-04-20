package net.kigawa.leafia.server.usecase.storage

import net.kigawa.leafia.server.domain.ProjectId
import net.kigawa.leafia.server.domain.ProjectState

interface StateStorage {
    fun loadState(projectId: ProjectId): ProjectState?
    fun saveState(projectId: ProjectId, state: ProjectState)
}