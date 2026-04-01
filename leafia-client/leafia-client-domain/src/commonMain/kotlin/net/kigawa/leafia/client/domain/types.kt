package net.kigawa.leafia.client.domain

import net.kigawa.kodel.api.err.Res

typealias RawAccessToken = String

@Suppress("unused")
inline fun <reified T, reified E: Throwable> Res<T, E>.unwrap(onErr: (E) -> T): T = when (this) {
    is Res.Ok<T, E> -> value
    is Res.Err<T, E> -> onErr(err)
}
