name := "dbWebApi"

version := "0.0.1"

scalaVersion := "2.12.4"

val akkaHttpVersion = "10.0.10"

libraryDependencies += "com.typesafe.akka" %% "akka-http" % akkaHttpVersion
libraryDependencies += "commons-daemon" % "commons-daemon" % "1.0.15"
libraryDependencies += "com.typesafe.scala-logging" %% "scala-logging" % "3.7.2"
libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"
libraryDependencies += "org.mongodb.scala" %% "mongo-scala-driver" % "2.1.0"

assemblyJarName in assembly := name.value + "_" + version.value + "_" + scalaVersion.value + ".jar"

