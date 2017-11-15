package lart.routeParts

import akka.http.scaladsl.model.StatusCodes.PermanentRedirect
import lart.webService.WebServicePart

import lart.appSettings._

object HelpFromFiles extends WebServicePart {

  override def RouteGenerator = {
    val result = get {
      path("fonts" / Segment) { name ⇒
        getFromResource(s"helpHttp/fonts/$name")
      } ~ path("helpHttp" / Segment) { name ⇒
        getFromResource(s"helpHttp/$name")
      } ~ pathSingleSlash {
        redirect("helpHttp/index.html", PermanentRedirect)
      }
    }
    appLogger.debug("Created route - routeFiles")
    result
  }
}
