package org.lart.webService

import akka.http.scaladsl.server.{Directives, Route}

trait WebServicePart extends Directives {
  lazy val route: Route = RouteGenerator
  def RouteGenerator: Route
}
