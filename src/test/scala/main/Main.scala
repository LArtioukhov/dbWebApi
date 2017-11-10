package main

import lart.webService.WebServiceDaemon

/** *
  * Needed only for run from IDE.
  * Real start class WebServiceDaemon
  * */
object Main extends App {

  val webServer = createWebServer()

  private[this] var cleanupAlreadyRun: Boolean = false

  def createWebServer() = new WebServiceDaemon

  def cleanup() {
    val previouslyRun = cleanupAlreadyRun
    cleanupAlreadyRun = true
    if (!previouslyRun) webServer.stop()
  }

  Runtime.getRuntime.addShutdownHook(new Thread(() => {
    cleanup()
  }))

  webServer.start()
}
