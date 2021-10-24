package io.github.msengbusch.murraycraft.cloud.util

import java.util.*

class GraphNode<K, V>(val key: K, val value: V) {
    val neighbours = mutableListOf<GraphNode<K, V>>()
}

object TopologicalSort {
    fun <K, V> topologicalSort(graph: List<GraphNode<K, V>>): List<GraphNode<K, V>> {
        val indegree = mutableMapOf<GraphNode<K, V>, Int>()
        for(node in graph) {
            indegree[node] = 0
        }

        for(node in graph) {
            for(neighbour in node.neighbours) {
                indegree[neighbour] = indegree[neighbour]!!.plus(1)
            }
        }

        val order = mutableListOf<GraphNode<K, V>>()
        val queue = LinkedList<GraphNode<K, V>>()

        for(node in graph) {
            if(indegree[node] == 0) {
                queue.offer(node)
                order.add(node)
            }
        }

        while(!queue.isEmpty()) {
            val node = queue.poll()
            for(neighbour in node.neighbours) {
                indegree[neighbour] = indegree[neighbour]!!.minus(1)
                if(indegree[neighbour] == 0) {
                    queue.offer(neighbour)
                    order.add(neighbour)
                }
            }
        }

        if(order.size != graph.size) {
            throw IllegalArgumentException("Failed to create graph. There must be a cycle")
        }

        return order
    }
}