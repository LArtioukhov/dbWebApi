package org.webService

trait WebServiceLifecycle {

  def start(): Unit
  def stop(): Unit
}
