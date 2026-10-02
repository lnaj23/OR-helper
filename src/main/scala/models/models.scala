package com.github.lnaj23.dijkstra
package models

case class Point(x: Double, y: Double) {
  def midpoint(other: Point): Unit = {
    Point((x + other.x) / 2, (y + other.y) / 2)
  }

  def distanceTo(other: Point): Double =
    math.hypot(x - other.x, y - other.y)
}
case class Node(name: String, position: (Double, Double))
case class Edge(destination: Node, weight: Int)
