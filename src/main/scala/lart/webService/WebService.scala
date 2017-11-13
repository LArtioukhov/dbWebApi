package lart.webService

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpRequest, HttpResponse}
import akka.stream.ActorMaterializer

import lart.helpers.AppSettings._

import scala.concurrent.{ExecutionContextExecutor, Future}

object WebService extends WebServiceLifecycle {

  private[this] var started: Boolean                    = false
  private var bindingFuture: Future[Http.ServerBinding] = _

  private val webServiceName = appName

  implicit val system: ActorSystem                        = ActorSystem(webServiceName)
  implicit val materializer: ActorMaterializer            = ActorMaterializer()
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  val http = Http(system)

  def response(httpRequest: HttpRequest): Future[HttpResponse] =
    http.singleRequest(httpRequest)

  override def start(): Unit = {
    if (!started) {
      appLogger.info(s"Starting $webServiceName daemon")
      started = true
      bindingFuture = http.bindAndHandle(WebServiceInit.route, defaultHost, defaultPort)
    }
  }

  override def stop(): Unit = {
    if (started) {
      appLogger.info(s"Stopping $webServiceName daemon")
      started = false
      http.shutdownAllConnectionPools()
      bindingFuture.flatMap(_.unbind()).onComplete(_ ⇒ system.terminate())
      appLogger.info(s"Web service $webServiceName stopped")
    }
  }

}
