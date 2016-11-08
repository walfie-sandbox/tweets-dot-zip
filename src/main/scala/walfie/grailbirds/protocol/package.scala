package walfie.grailbirds.protocol

import boopickle.Default._
import scala.scalajs.js
import scala.scalajs.js.annotation._
import walfie.grailbirds.domain._

case class ClientMessage[T <: Request[_]](
  streamId: Int,
  data:     T
)

sealed trait Request[T <: Response]
object Request {
  implicit val pickler: Pickler[Request] = generatePickler[Request]
  case class Search(
    regex: String
  ) extends Request[Response.Search]
}

case class WorkerMessage[T <: Response](
  streamId: Int,
  data:     T
)

sealed trait Response
object Response {
  implicit val pickler: Pickler[Response] = generatePickler[Response]

  case class Search(
    tweets: Seq[Tweet]
  ) extends Response
}

