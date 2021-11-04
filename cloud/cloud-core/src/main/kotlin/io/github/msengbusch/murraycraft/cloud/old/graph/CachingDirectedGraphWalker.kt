package io.github.msengbusch.murraycraft.cloud.old.graph

import kotlin.math.min

// Copied from https://github.com/gradle/gradle/blob/master/subprojects/model-core/src/main/java/org/gradle/api/internal/tasks/CachingTaskDependencyResolveContext.java
class CachingDirectedGraphWalker<N, T>(private val graph: DirectedGraphWithEdgeValues<N, T>) {
    private val startNodes = mutableListOf<N>()
    private val strongComponents = linkedSetOf<NodeDetails<N, T>>()
    private val cachedNodeValues = mutableMapOf<N, Set<T>>()

    constructor(graph: DirectedGraph<N, T>) : this(GraphWithEmptyEdges(graph))

    fun add(values: List<N>) {
        startNodes.addAll(values)
    }

    fun findValues(): Set<T> {
        try {
            return doSearch()
        } finally {
            startNodes.clear()
        }
    }

    fun findCycles(): List<Set<N>> {
        findValues()
        val result = mutableListOf<Set<N>>()
        for(nodeDetails in strongComponents) {
            val componentMembers = linkedSetOf<N>()
            for(componentMember in nodeDetails.componentMembers) {
                componentMembers.add(componentMember.node)
            }
            result.add(componentMembers)
        }
        return result
    }

    private fun doSearch(): Set<T> {
        var componentCount = 0
        val seenNodes = mutableMapOf<N, NodeDetails<N, T>>()
        val components = mutableMapOf<Int, NodeDetails<N, T>>()
        val queue = ArrayDeque(startNodes)

        while(!queue.isEmpty()) {
            val node = queue.first()
            var details = seenNodes[node]

            if(details == null) {
                details = NodeDetails(node, componentCount++)
                seenNodes[node] = details
                components[details.component] = details

                val cacheValues = cachedNodeValues[node]
                if(cacheValues != null) {
                    details.values = cacheValues as LinkedHashSet<T>
                    details.finished = true
                    queue.removeFirst()
                    continue
                }

                graph.getNodeValues(node, details.values, details.successors)
                for(connectedNode in details.successors) {
                    val connectedNodeDetails = seenNodes[connectedNode]
                    if(connectedNodeDetails == null) {
                        queue.addFirst(connectedNode)
                    } else if(!connectedNodeDetails.finished) {
                        details.stronglyConnected = true
                    }
                }
            } else {
                queue.removeFirst()

                if(cachedNodeValues.containsKey(node)) {
                    continue
                }

                for(connectedNode in details.successors) {
                    val connectedNodeDetails = seenNodes[connectedNode]!!
                    if(!connectedNodeDetails.finished) {
                        val minSeen = min(details.minSeen, connectedNodeDetails.minSeen)
                        details.minSeen = minSeen
                        connectedNodeDetails.minSeen = minSeen
                        details.stronglyConnected = true
                    }

                    details.values.addAll(connectedNodeDetails.values)
                    graph.getEdgeValues(node, connectedNode, details.values)
                }

                if(details.minSeen != details.component) {
                    val rootDetails = components[details.minSeen]!!
                    rootDetails.values.addAll(details.values)
                    details.values.clear()
                    rootDetails.componentMembers.addAll(details.componentMembers)
                } else {
                    for(componentMember in details.componentMembers) {
                        cachedNodeValues[componentMember.node] = details.values
                        componentMember.finished = true
                        components.remove(componentMember.component)
                    }

                    if(details.stronglyConnected) {
                        strongComponents.add(details)
                    }
                }
            }
        }

        val values = linkedSetOf<T>()
        for(startNode in startNodes) {
            values.addAll(cachedNodeValues[startNode]!!)
        }

        return values;
    }

    private class NodeDetails<N, T>(val node: N, val component: Int) {
        var values: LinkedHashSet<T> = linkedSetOf()
        val successors = mutableListOf<N>()
        val componentMembers = linkedSetOf<NodeDetails<N, T>>()

        var minSeen = component
        var stronglyConnected = false
        var finished = false

        init {
            componentMembers.add(this)
        }
    }

    private class GraphWithEmptyEdges<N, T>(val graph: DirectedGraph<N, T>) : DirectedGraphWithEdgeValues<N, T> {
        override fun getNodeValues(node: N, values: Collection<T>, connectedNodes: Collection<N>) = graph.getNodeValues(node, values, connectedNodes)

        override fun getEdgeValues(from: N, to: N, values: Collection<T>) {
            // Empty
        }
    }
}