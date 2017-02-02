package walfie.grailbirds.client

import org.scalajs.dom.raw._
import scala.scalajs.js
import scala.scalajs.js.annotation._
import walfie.grailbirds.domain._
import walfie.grailbirds.protocol._

@JSExport("GrailbirdsClient")
object Application {
  @JSExport
  def init(worker: Worker) {
    worker.onmessage = { e: MessageEvent =>
      // TODO: Handle different types
      val message = e.data.asInstanceOf[WorkerMessage[js.Array[Tweet]]]

      message.data.foreach { tweet =>
        println(tweet.createdAt + " " + tweet.text)
      }
    }

    val msg = new ClientMessage.Search(1, "saegusa kii")

    worker.postMessage(msg)
  }

}

