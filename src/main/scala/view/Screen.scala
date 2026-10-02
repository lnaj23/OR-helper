package com.github.lnaj23.dijkstra
package view
import doodle.image.*
import doodle.core.*
import doodle.image.syntax.all.*
import doodle.java2d.*
import cats.effect.unsafe.implicits.global
import com.github.lnaj23.dijkstra.models.{Edge, Node}
import config.AppConfig
import doodle.core.font.*


object Screen {
  val nodeSize: Int = AppConfig.nodeSize
  val frame =
    Frame.default.withSize(AppConfig.windowWidth, AppConfig.windowHeight).withBackground(Color.midnightBlue)

  def initWindow(graph: Map[Node, List[Edge]]): Unit = {
    initImages(graph).drawWithFrame(frame)
  }

  private def initImages(graph: Map[Node, List[Edge]]): Image = {
    val images = graph.map { case (node, edges) =>
      val imageNode = drawNode(node).at(node.position._1, node.position._2)
      val imageEdges = drawEdges(node, edges)

      imageNode on imageEdges
    }

    images.foldLeft(Image.empty) { (accumulateur, nouvelleImage) =>
      accumulateur on nouvelleImage
    }
  }

  private def drawNode(node: Node): Image = {
    val circle = Image.circle(AppConfig.nodeSize)
      .fillColor(Color.red)

    val text = Image.text(node.name)
      .strokeColor(Color.blueViolet)
      .fillColor(Color.royalBlue)
      .font(Font.defaultSerif.withBold.withSize(FontSize.points(AppConfig.mediumFont)))
      .at(0, AppConfig.nodeSize + 5)

    text on circle
  }

  private def drawEdges(node: Node, edges: List[Edge]): Image = {
    val posSourceNode = node.position

    val allPaths = edges.map(edge => OpenPath.empty.moveTo(posSourceNode._1, posSourceNode._2).lineTo(edge.destination.position._1, edge.destination.position._2))
    val edgesPath = allPaths.map(path => Image.path(path).strokeColor(Color.yellow))
    val edgesImage = edgesPath.foldLeft(Image.empty) { (accumulateur, nouvelleImage) =>
      accumulateur on nouvelleImage
    }

    val textImages = edges.map(edge => Image.text(s"${edge.weight}").strokeColor(Color.white)
          .fillColor(Color.royalBlue)
          .font(Font.defaultSerif.withBold.withSize(FontSize.points(12)))
          .at((edge.destination.position._1 + posSourceNode._1) / 2, (edge.destination.position._2 + posSourceNode._2) / 2))

    val textImage = textImages.foldLeft(Image.empty) { (accumulateur, nouvelleImage) =>
      accumulateur on nouvelleImage
    }

    edgesImage on textImage
  }
}
