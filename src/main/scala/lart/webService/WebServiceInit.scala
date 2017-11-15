package lart.webService

import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import lart.routeParts.{DocCollection, HelpFromFiles}
import lart.mongoDB.{MongoDBCollectionConnector, MongoDBConnector}

import lart.appSettings._

/**
  * Singleton object for initialization
  */
object WebServiceInit {

  appLogger.debug(s"Starting Web service $appName initialization")

  val route: Route =
    mongoInit(HelpFromFiles.route)

  appLogger.debug(s"Web service $appName initialization completed successfully")

  def mongoInit(startRoute: Route): Route = {
    def getRoute(str: String): Route = {
      DocCollection(
        MongoDBCollectionConnector(
          MongoDBConnector(mongoDBSettings.host, mongoDBSettings.port)
            .getCollection(str))).RouteGenerator
    }
    mongoDBSettings.collections.foldRight(startRoute)(getRoute(_) ~ _)
  }

}
