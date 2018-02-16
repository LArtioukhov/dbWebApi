package lart.webService

import scala.concurrent.Future

import akka.http.scaladsl.{Http, HttpExt}

import lart.appSettings._

object WebService extends WebServiceLifecycle {

  private[this] var started: Boolean                    = false
  private var bindingFuture: Future[Http.ServerBinding] = _
  private val http                                      = implicitly[HttpExt]

  override def start(): Unit = {
    if (!started) {
      appLogger.debug(s"Starting $appName daemon")
      started = true
      bindingFuture = http.bindAndHandle(WebServiceInit.route, defaultHost, defaultPort)
      appLogger.info(s"The web service $appName is launched at http://$defaultHost:$defaultPort")
    }
  }

  override def stop(): Unit = {
    if (started) {
      appLogger.debug(s"Stopping $appName daemon")
      started = false
      http.shutdownAllConnectionPools()
      bindingFuture.flatMap(_.unbind()).onComplete(_ ⇒ actorSystem.terminate())
      appLogger.info(s"Web service $appName stopped")
    }
  }

}
