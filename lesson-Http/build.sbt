scalaVersion := "2.13.8"

name := "restapi"
organization := "template"

val akkaVersion = "2.6.8"
val akkaHttpVersion = "10.2.9"
val akkaHttpJsonSerializersVersion = "1.34.0"

libraryDependencies ++= Seq(
  //  akka streams
  "com.typesafe.akka" %% "akka-stream" % akkaVersion,
  //  akka http
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "de.heikoseeberger" %% "akka-http-circe" % akkaHttpJsonSerializersVersion,

  "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
  "org.scalatest" %% "scalatest" % "3.2.12" % Test,
  "com.softwaremill.sttp.client3" %% "core" % "3.6.1" % Test
)
