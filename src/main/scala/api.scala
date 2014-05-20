package bitshow

import unfiltered.request._
import unfiltered.response._

import net.liftweb.json.JsonAST._
import net.liftweb.json.Extraction._
import net.liftweb.json.Printer._

import argonaut.integrate.unfiltered.JsonResponse

/** This filter should handle conversions requested
 * by the client page, serve listings of available
 * images and converters, and serve the images themselves */
object API extends unfiltered.filter.Plan {
  def intent = {
    case GET(Path(Seg("images" :: id :: Nil))) =>
      DefaultStore.get(id).map(i => ContentType(i.contentType) ~> ResponseBytes(i.bytes)).getOrElse(NotFound)

    case GET(Path("/test")) =>
      val bytes = org.apache.commons.io.IOUtils.toByteArray(
        getClass.getResource("/www/img/ny-scala.png").openStream
      )
      val logo = Item("image/png", bytes)
      val item = GraySquare(logo)
      ContentType(item.contentType) ~> ResponseBytes(item.bytes)
  }
}

object JsonApi extends unfiltered.filter.Plan {
  // http://stackoverflow.com/questions/6271386/how-do-you-serialize-a-map-to-json-in-scala#6271565
  implicit val formats = net.liftweb.json.DefaultFormats
  def intent = {
    case POST(Path(Seg("convert" :: id :: converter :: Nil))) =>
      val c = java.net.URLDecoder.decode(converter, "UTF-8")
      val optI = Converters(c).flatMap(DefaultStore.get(id).map)
      optI.map(DefaultStore.put).map(id => JsonResponse(compact(render(decompose("id" -> id))))).getOrElse(NotFound)

    case GET(Path("/converters")) => JsonResponse(compact(render(decompose(Converters.names))))

    case GET(Path("/images")) => JsonResponse(compact(render(decompose(DefaultStore.list))))
  }
}
