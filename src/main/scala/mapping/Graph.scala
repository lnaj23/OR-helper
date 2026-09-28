package com.github.lnaj23.dijkstra
package mapping
import com.github.lnaj23.dijkstra.models.{Edge, Node}

import scala.util.Random

class Graph {
  val start = Node("START", (-300.0, 0.0))
  val end = Node("END", (300.0, 0.0))

  def initRandomMap(n: Int): Map[Node, List[Edge]] = {
    val randomNodes: List[Node] = List.tabulate(n)(index =>
      Node(s"$index", (Random.between(-250, 250), Random.between(-100, 100)))
    )
    val nodes = List(start, end) ++ randomNodes

    nodes.map(node => (node, createRandomEdge(nodes))).toMap
  }

  private def createRandomEdge(nodes: List[Node]): List[Edge] = {
    val shuffleNodes = Random.shuffle(nodes)
    List(Edge(shuffleNodes.head, Random.nextInt(10) + 1), Edge(shuffleNodes.last, Random.nextInt(10) + 1))
  }
}
