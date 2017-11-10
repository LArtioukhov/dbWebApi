package lart.helpers

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._

object AppSettings {

  case class MongoDBSettings(host: String,
                             port: Int,
                             dataBaseName: String,
                             collections: Set[String])

  lazy val appLogger = Logger(LoggerFactory.getLogger(appName))

  private val settings = ConfigFactory.load()

  lazy val appName: String     = settings getString "webService.appName"
  lazy val defaultHost: String = settings getString "webService.host"
  lazy val defaultPort: Int    = settings getInt "webService.port"
  lazy val mongoDBSettings = MongoDBSettings(
    host = settings getString "mongoDB.host",
    port = settings getInt "mongoDB.port",
    dataBaseName = settings getString "mongoDB.dataBaseName",
    collections = (settings getStringList "mongoDB.collections").asScala.toSet
  )
}
