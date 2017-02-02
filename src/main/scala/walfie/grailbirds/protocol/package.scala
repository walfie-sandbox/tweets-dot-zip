package walfie.grailbirds.protocol

import scala.scalajs.js
import scala.scalajs.js.annotation._
import walfie.grailbirds.domain._

@ScalaJSDefined
trait Message extends js.Object {
  val streamId: Int
}

object MessageType {
  val Search = 0
}

@ScalaJSDefined
sealed trait ClientMessage[ResponseT <: js.Any] extends Message {
  val `type`: Int
}

object ClientMessage {
  @ScalaJSDefined
  class Search(
    val streamId: Int,
    val regex:    String
  ) extends ClientMessage[js.Array[Tweet]] {
    val `type` = MessageType.Search
  }

  def from(obj: js.Object): ClientMessage[_] = {
    import walfie.grailbirds.protocol.{MessageType => MT}

    obj.asInstanceOf[ClientMessage[js.Any]].`type` match {
      case MT.Search => obj.asInstanceOf[ClientMessage.Search]
    }
  }
}

@ScalaJSDefined
trait WorkerMessage[T <: js.Any] extends Message {
  val data: T
}

