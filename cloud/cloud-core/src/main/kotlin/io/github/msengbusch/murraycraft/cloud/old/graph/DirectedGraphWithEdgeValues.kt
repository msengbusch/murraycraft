package io.github.msengbusch.murraycraft.cloud.old.graph

interface DirectedGraphWithEdgeValues<N, V> : DirectedGraph<N, V> {
    fun getEdgeValues(from: N, to: N, values: Collection<V>)
}