package com.github.lnaj23.dijkstra
package view
import doodle.image.*
import doodle.core.*
import doodle.image.syntax.all.*
import doodle.java2d.*
import cats.effect.unsafe.implicits.global
import com.github.lnaj23.dijkstra.models.{Edge, Node}
import doodle.core.font.*


object Screen {
  val frame =
    Frame.default.withSize(800, 600).withBackground(Color.midnightBlue)

  def initNodes(nodes: Map[Node, List[Edge]]): Unit = {
    val images = nodes.map { case (node, edges) =>
      val imageNode = drawNode(node).foldLeft(Image.empty)(_ under _)
      val imageEdges = drawEdges(node, edges).foldLeft(Image.empty)(_ under _)

      imageNode.on(imageEdges)
    }.toList

    val finalImage = images.foldLeft(Image.empty)(_ on _)
    finalImage.drawWithFrame(frame)
  }

  private def drawNode(node: Node): List[Image] = {
    List(Image.circle(20)
      .fillColor(Color.red)
      .at(node.position._1, node.position._2),
      Image.text(node.name).strokeColor(Color.blueViolet)
      .fillColor(Color.royalBlue)
      .font(Font.defaultSerif.withBold.withSize(FontSize.points(24)))
        .at(node.position._1, node.position._2 + 20)
    )
  }

  private def drawEdges(node: Node, edges: List[Edge]): List[Image] = {
    val posSourceNode = node.position
    val posDestinationsNode: List[(Double, Double)] = edges.map(edge => edge.destination.position)

    val allPaths = posDestinationsNode.map(dest => OpenPath.empty.moveTo(posSourceNode._1, posSourceNode._2).lineTo(dest._1, dest._2))
    allPaths.map(path => Image.path(path).strokeColor(Color.yellow))
  }
}
