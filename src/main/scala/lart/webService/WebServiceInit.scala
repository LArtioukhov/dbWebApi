package lart.webService

import akka.http.scaladsl.server.Route
import lart.routeParts.HelpFromFiles

import lart.helpers.AppSettings._

/**
  * Singleton object for initialization
  */
object WebServiceInit {

  appLogger.debug(s"Starting Web service $appName initialization")

  val route: Route =
    HelpFromFiles.route
  // Add your web routes here
  //~ [T extends WebServicePart].route

  appLogger.debug(s"Web service $appName initialization completed successfully")

}
