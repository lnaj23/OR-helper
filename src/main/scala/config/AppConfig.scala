package config

import com.typesafe.config.ConfigFactory

object AppConfig {
  private val config = ConfigFactory.load()

  val windowWidth: Int = config.getInt("view.width")
  val windowHeight: Int = config.getInt("view.height")
  val nodeSize: Int = config.getInt("view.node-size")

  val edgeWeight: Int = config.getInt("model.edge-max-weight")
  val smallFont: Int = config.getInt("view.small-font-size")
  val mediumFont: Int = config.getInt("view.medium-font-size")

  val startNodePosition: (Double, Double) = (-windowWidth/2 + nodeSize*2, 0)
  val endNodePosition: (Double, Double) = (windowWidth/2 - nodeSize*2, 0)
  val otherNodeMaxX: (Double, Double) = (startNodePosition._1 + 50, endNodePosition._1 - 50)
  val otherNodeMaxY: (Double, Double) = (startNodePosition._2 - 200, startNodePosition._2 + 200)
  
}