name := "webService"

version := "0.1"

scalaVersion := "2.12.4"

assemblyJarName in assembly := name.value + "_" + version.value + "_" + scalaVersion.value + ".jar"

val akkaHttpVersion = "10.0.10"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
libraryDependencies += "commons-daemon" % "commons-daemon" % "1.0.15"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
