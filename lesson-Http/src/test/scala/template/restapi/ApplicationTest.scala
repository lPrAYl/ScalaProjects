package template.restapi

import org.scalatest.matchers.should.Matchers
import org.scalatest.wordspec.AnyWordSpec

import sttp.client3._

class ApplicationTest extends AnyWordSpec with Matchers {
  "Service" should {
    "response on target port" in {
      val sort: Option[String] = None
      val query = "http language:scala"

      Application.start()
      val request = basicRequest.get(uri"https://api.github.com/search/repositories?q=$query&sort=$sort")

      implicit val backend: SttpBackend[Identity, Any] = HttpClientSyncBackend()
      request.send(backend).code shouldBe 200

    }
  }
}
