package org.lart.routeParts

import akka.http.scaladsl.model.StatusCodes.PermanentRedirect
import org.lart.helpers.AppSettings._
import org.lart.webService.WebServicePart

object HelpFromFiles extends WebServicePart {

  override def RouteGenerator = {
    val result = get {
      path("fonts" / Segment) { name ⇒
        getFromResource(s"helpHttp/fonts/$name")
      } ~ path("helpHttp" / Segment) { name ⇒
        getFromResource(s"helpHttp/$name")
      } ~ pathSingleSlash {
        //getFromResource("helpHttp/index.html")
        redirect("helpHttp/index.html", PermanentRedirect)
      }
    }
    appLogger.debug("Created route - routeFiles")
    result
  }
}
