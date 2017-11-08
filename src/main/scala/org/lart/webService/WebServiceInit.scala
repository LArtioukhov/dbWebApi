package org.lart.webService

import akka.http.scaladsl.server.Route
import org.lart.helpers.AppSettings._
import org.lart.routeParts.HelpFromFiles

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
