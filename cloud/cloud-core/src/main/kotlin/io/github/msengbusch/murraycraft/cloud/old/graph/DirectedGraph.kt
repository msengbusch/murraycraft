package io.github.msengbusch.murraycraft.cloud.old.graph

interface DirectedGraph<N, V> {
    fun getNodeValues(node: N, values: Collection<V>, connectedNodes: Collection<N>)
}