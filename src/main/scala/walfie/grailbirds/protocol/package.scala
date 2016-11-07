package walfie.grailbirds.protocol

import scala.scalajs.js
import scala.scalajs.js.annotation._

@ScalaJSDefined
trait ClientMessage extends js.Object {
  val streamId: Int
  val requestType: Int
  val data: js.Any
}

object ClientMessage {
  object RequestType {
    val Search = 0
  }

  @ScalaJSDefined
  trait Request extends js.Object

  @ScalaJSDefined
  trait Search extends Request {
    val regex: String
  }
}

@ScalaJSDefined
trait WorkerMessage extends js.Object {
  val streamId: Int
  val data: js.Any
}

object WorkerMessage {
  def apply(_streamId: Int, _data: js.Any): WorkerMessage = new WorkerMessage {
    val streamId = _streamId
    val data = _data
  }
}

