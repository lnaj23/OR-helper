package com.github.lnaj23.dijkstra
package models

case class Node(name: String, position: (Double, Double))
case class Edge(destination: Node, weight: Int)
