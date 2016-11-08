package walfie.grailbirds.client

import boopickle.Default._
import java.nio.ByteBuffer
import org.scalajs.dom.raw._
import scala.scalajs.js
import scala.scalajs.js.annotation._
import walfie.grailbirds.protocol._

@JSExport("GrailbirdsClient")
object Application {
  @JSExport
  def init(worker: Worker) {
    worker.onmessage = { e: MessageEvent =>
      val message = Unpickle[WorkerMessage[_]].fromBytes(e.data.asInstanceOf[ByteBuffer])

      message.data match {
        case req: Response.Search =>
          req.tweets.foreach { tweet =>
            println(tweet.createdAt + " " + tweet.text)
          }
      }
    }

    val msg = ClientMessage(1, Request.Search("saegusa kii"))
    val msgBytes = Pickle.intoBytes(msg)

    worker.postMessage(msg, js.Array(msgBytes))
  }

}

