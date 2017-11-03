package org.webServer

import org.apache.commons.daemon.{Daemon, DaemonContext}

abstract class AbstractWebServiceDaemon extends Daemon {

  def webServer: WebServiceLifecycle
  override def init(context: DaemonContext): Unit = {}
  override def start(): Unit                      = webServer.start()
  override def stop(): Unit                       = webServer.stop()
  override def destroy(): Unit                    = webServer.stop()
}
