package lart

import akka.actor.ActorSystem
import akka.http.scaladsl.{Http, HttpExt}
import akka.stream.ActorMaterializer
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger

import scala.collection.JavaConverters._
import scala.concurrent.ExecutionContextExecutor

package object appSettings {

  case class MongoDBSettings(host: String,
                             port: Int,
                             dataBaseName: String,
                             collections: List[String])

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

  lazy val appLogger = Logger(appName)

  implicit val actorSystem: ActorSystem                           = ActorSystem(appName)
  implicit val actorMaterializer: ActorMaterializer               = ActorMaterializer()
  implicit val executionContextExecutor: ExecutionContextExecutor = actorSystem.dispatcher
  implicit val httpExt: HttpExt                                   = Http(actorSystem)

}
