package com.github.lnaj23.dijkstra

import com.github.lnaj23.dijkstra.view.Screen
import mapping.Graph

@main def startApp(): Unit = {
  val graph = Graph()
  val randomizedGraph = graph.initRandomMap(50)
  Screen.initNodes(randomizedGraph)
}