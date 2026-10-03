package com.github.lnaj23.dijkstra
package view
import doodle.image.*
import doodle.core.*
import doodle.image.syntax.all.*
import doodle.java2d.*
import cats.effect.unsafe.implicits.global
import com.github.lnaj23.dijkstra.models.{Edge, Node, Point}
import config.AppConfig
import doodle.core.font.*

extension (point: models.Point)
  def toDoodle: doodle.core.Point =
    doodle.core.Point(point.x, point.y)

object Screen {
  val frame =
    Frame.default.withSize(AppConfig.windowWidth, AppConfig.windowHeight).withBackground(Color.midnightBlue)

  def initWindow(graph: Map[Node, List[Edge]]): Unit = {
    initImages(graph).drawWithFrame(frame)
  }

  private def combine(images: Iterable[Image]): Image =
    images.foldLeft(Image.empty)(_ on _)

  private def initImages(graph: Map[Node, List[Edge]]): Image = {
    val edgesImage = combine(graph.map { case (node, edges) => drawEdges(node, edges) })
    val nodesImage = combine(graph.keys.map(node => drawNode(node).at(node.position.toDoodle)))

    nodesImage on edgesImage
  }

  private def drawNode(node: Node): Image = {
    val circle = Image.circle(AppConfig.nodeSize)
      .fillColor(Color.red)

    val text = Image.text(node.name)
      .strokeColor(Color.blueViolet)
      .fillColor(Color.royalBlue)
      .font(Font.defaultMonospaced.withBold.withSize(FontSize.points(AppConfig.mediumFont)))
      .at(0, AppConfig.nodeSize + 5)

    text on circle
  }

  private def drawEdges(node: Node, edges: List[Edge]): Image = {
    val posSourceNode: Point = node.position

    val allPaths = edges.map(edge => OpenPath.empty.moveTo(posSourceNode.x, posSourceNode.y).lineTo(edge.destination.position.x, edge.destination.position.y))
    val edgesPath = allPaths.map(path => Image.path(path).strokeColor(Color.yellow))
    val edgesImage = edgesPath.foldLeft(Image.empty) { (accumulateur, nouvelleImage) =>
      accumulateur on nouvelleImage
    }

    val textImages = edges.map(edge => Image.text(s"${edge.weight}").strokeColor(Color.white)
          .fillColor(Color.royalBlue)
          .font(Font.defaultMonospaced.withBold.withSize(AppConfig.smallFont)).at(0, 10)
          .at(posSourceNode.midpoint(edge.destination.position).toDoodle)
    )

    val textImage = combine(textImages)

    edgesImage on textImage
  }
}
