name := "dbWebApi"

version := "0.0.3"

scalaVersion := "2.12.4"

val akkaHttpVersion = "10.0.11"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
libraryDependencies += "commons-daemon" % "commons-daemon" % "1.1.0"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.2.1"

assemblyJarName in assembly := name.value + "_" + version.value + "_" + scalaVersion.value + ".jar"

