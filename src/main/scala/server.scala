package bitshow

import unfiltered.request._
import unfiltered.response._

/** embedded server */
object Server {
  def main(args: Array[String]) {
    unfiltered.jetty.Http(8080).context("/assets") { ctx =>
      ctx.resources(new java.net.URL(
        getClass.getResource("/www/css"), "."))
    }
    .filter(Browse)
    .filter(API)
    .filter(JsonApi)
    .run({ server =>
      unfiltered.util.Browser.open(server.url)
    })
  }
}
