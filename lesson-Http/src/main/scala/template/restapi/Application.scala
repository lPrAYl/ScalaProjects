package template.restapi

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.StandardRoute

import scala.concurrent.ExecutionContextExecutor


object Application extends App {
  def start(): Unit = {
    implicit val system: ActorSystem = ActorSystem()
    implicit val materializer: ActorMaterializer.type = ActorMaterializer
    implicit val ec: ExecutionContextExecutor = system.dispatcher

    val routes: StandardRoute = complete("Running ...")

    Http().newServerAt("localhost", 8000).bind(routes)
  }

  start()
}
