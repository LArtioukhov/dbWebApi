package org.webServer

trait WebServiceLifecycle {

  def start(): Unit
  def stop(): Unit
}
