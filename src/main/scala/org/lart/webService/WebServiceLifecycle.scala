package org.lart.webService

trait WebServiceLifecycle {

  def start(): Unit
  def stop(): Unit
}
