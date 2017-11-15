package lart.webService

import scala.concurrent.Future

import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.http.scaladsl.{Http, HttpExt}

import lart.appSettings._

object WebService extends WebServiceLifecycle {

  private[this] var started: Boolean                    = false
  private var bindingFuture: Future[Http.ServerBinding] = _

  val http: HttpExt = Http(system)

  def response(httpRequest: HttpRequest): Future[HttpResponse] =
    http.singleRequest(httpRequest)

  override def start(): Unit = {
    if (!started) {
      appLogger.info(s"Starting $appName daemon")
      started = true
      bindingFuture = http.bindAndHandle(WebServiceInit.route, defaultHost, defaultPort)
    }
  }

  override def stop(): Unit = {
    if (started) {
      appLogger.info(s"Stopping $appName daemon")
      started = false
      http.shutdownAllConnectionPools()
      bindingFuture.flatMap(_.unbind()).onComplete(_ ⇒ system.terminate())
      appLogger.info(s"Web service $appName stopped")
    }
  }

}
