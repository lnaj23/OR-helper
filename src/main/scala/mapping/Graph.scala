package com.github.lnaj23.dijkstra
package mapping
import com.github.lnaj23.dijkstra.models.{Edge, Node, Point}
import config.AppConfig

import scala.util.Random

class Graph {
  val start = Node("START", AppConfig.startNodePosition)
  val end = Node("END", AppConfig.endNodePosition)

  def initRandomMap(n: Int): Map[Node, List[Edge]] = {
    val randomNodes: List[Node] = List.tabulate(n)(index =>
      Node(s"$index", Point(Random.between(AppConfig.rangeNodeMaxX.min, AppConfig.rangeNodeMaxX.max),
        Random.between(AppConfig.rangeNodeMaxY.min, AppConfig.rangeNodeMaxY.max)))
    )
    val nodes = List(start, end) ++ randomNodes

    nodes.map(node => (node, createRandomEdge(nodes))).toMap
  }

  private def createRandomEdge(nodes: List[Node]): List[Edge] = {
    val shuffleNodes = Random.shuffle(nodes)
    List(Edge(shuffleNodes.head, Random.nextInt(10) + 1), Edge(shuffleNodes.last, Random.nextInt(10) + 1))
  }
}
