package lart

import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContextExecutor

package object appSettings {
  case class MongoDBSettings(host: String,
                             port: Int,
                             dataBaseName: String,
                             collections: List[String])

  lazy val appLogger = Logger(LoggerFactory.getLogger(appName))

  private val settings         = ConfigFactory.load()
  lazy val appName: String     = settings getString "webService.appName"
  lazy val defaultHost: String = settings getString "webService.host"
  lazy val defaultPort: Int    = settings getInt "webService.port"
  lazy val mongoDBSettings = MongoDBSettings(
    host = settings getString "mongoDB.host",
    port = settings getInt "mongoDB.port",
    dataBaseName = settings getString "mongoDB.dataBaseName",
    collections = settings.getStringList("mongoDB.collections").asScala.toList
  )

  implicit val system: ActorSystem                        = ActorSystem(appName)
  implicit val materializer: ActorMaterializer            = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

}
