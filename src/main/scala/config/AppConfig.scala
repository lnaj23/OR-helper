package config

import com.typesafe.config.ConfigFactory
import com.github.lnaj23.dijkstra.models.{Point, RangeNode}

object AppConfig {
  private val config = ConfigFactory.load()

  val windowWidth: Int = config.getInt("view.width")
  val windowHeight: Int = config.getInt("view.height")
  val nodeSize: Int = config.getInt("view.node-size")

  val edgeWeight: Int = config.getInt("model.edge-max-weight")
  val smallFont: Int = config.getInt("view.small-font-size")
  val mediumFont: Int = config.getInt("view.medium-font-size")

  val startNodePosition: Point = Point(-windowWidth/2 + nodeSize*2, 0)
  val endNodePosition: Point = Point(windowWidth/2 - nodeSize*2, 0)
  val rangeNodeMaxX: RangeNode = RangeNode(startNodePosition.x + 50, endNodePosition.x - 50)
  val rangeNodeMaxY: RangeNode = RangeNode(startNodePosition.y - 200, startNodePosition._2 + 200)
}