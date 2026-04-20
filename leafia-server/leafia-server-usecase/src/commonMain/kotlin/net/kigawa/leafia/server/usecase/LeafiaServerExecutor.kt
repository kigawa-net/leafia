package net.kigawa.leafia.server.usecase

import net.kigawa.leafia.server.domain.LeafiaServer
import net.kigawa.leafia.server.usecase.k8s.K8sClient
import net.kigawa.leafia.server.usecase.push.PushUsecase
import net.kigawa.leafia.server.usecase.state.StateUsecase
import net.kigawa.leafia.server.usecase.storage.FileStorage
import net.kigawa.leafia.server.usecase.storage.StateStorage

class LeafiaServerExecutor(
    private val server: LeafiaServer,
    private val k8sClient: K8sClient,
    private val fileStorage: FileStorage,
    private val stateStorage: StateStorage,
) {
    val push = PushUsecase(k8sClient, fileStorage)
    val state = StateUsecase(k8sClient, stateStorage)
}