package AkkaHttpJson

import akka.actor.typed.ActorSystem
import akka.actor.typed.javadsl.Behaviors
import akka.http.scaladsl.Http
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._

import java.util.UUID


case class Person(name: String, age: Int)
case class UserAdded(id: String, timestamp: Long)

//  step1
import spray.json._
//  step2
trait PersonJsonProtocol extends DefaultJsonProtocol {
  implicit val personFormat = jsonFormat2(Person)
  implicit val userAddedFormat = jsonFormat2(UserAdded)
}

//  step 3
object AkkaHttpJson extends PersonJsonProtocol with SprayJsonSupport {

  implicit val system = ActorSystem(Behaviors.empty, "AkkaHttpJson")

  val route: Route  = (path("api" / "user") & post) {
    entity(as[Person]) { person: Person =>
      complete(UserAdded(UUID.randomUUID().toString, System.currentTimeMillis()))

    }
  }

  def main(argc: Array[String]): Unit = {
    Http().newServerAt("localhost", 8081).bind(route)
  }
}
