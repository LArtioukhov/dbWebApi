package org.lart.helpers

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.Logger
import org.slf4j.LoggerFactory

object AppSettings {

  lazy val appLogger = Logger(LoggerFactory.getLogger(appName))

  private val settings = ConfigFactory.load()

  lazy val appName: String     = settings.getString("webService.appName")
  lazy val defaultHost: String = settings.getString("webService.host")
  lazy val defaultPort: Int    = settings.getInt("webService.port")
}
