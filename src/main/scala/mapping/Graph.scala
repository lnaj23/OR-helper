package com.github.lnaj23.dijkstra
package mapping
import com.github.lnaj23.dijkstra.models.{Edge, Node}

import scala.util.Random

class Graph {
  def initRandomMap(n: Int): Map[Node, List[Edge]] = {
    val nodes: List[Node] = List.tabulate(n)(index =>
      Node(s"$index", (Random.nextDouble()*500, Random.nextDouble()*500)))

    nodes.map(node => (node, createRandomEdge(nodes))).toMap
  }

  private def createRandomEdge(nodes: List[Node]): List[Edge] = {
    val shuffleNodes = Random.shuffle(nodes)
    List(Edge(shuffleNodes.head, Random.nextInt(10) + 1), Edge(shuffleNodes.last, Random.nextInt(10) + 1))
  }
}
