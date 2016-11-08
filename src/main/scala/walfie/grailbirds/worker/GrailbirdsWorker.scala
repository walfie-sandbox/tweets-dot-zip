package walfie.grailbirds.worker

import boopickle.Default._
import java.nio.ByteBuffer
import org.scalajs.dom.raw._
import scala.concurrent.duration._
import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.JSConverters._
import walfie.grailbirds.domain._
import walfie.grailbirds.protocol._

@JSExport("GrailbirdsWorker")
object GrailbirdsWorker {
  @JSExport
  def init(
    scope:   DedicatedWorkerGlobalScope,
    baseUrl: String
  ): GrailbirdsWorker = new GrailbirdsWorker(scope, baseUrl)
}

class GrailbirdsWorker(scope: DedicatedWorkerGlobalScope, baseUrl: String) {
  import monix.execution.Scheduler.Implicits.global

  val grailbird = new Grailbird(scope, baseUrl)

  scope.onmessage = { e: MessageEvent =>
    val message = Unpickle[ClientMessage[_]].fromBytes(e.data.asInstanceOf[ByteBuffer])

    message.data match {
      case req: Request.Search =>
        val regex = js.RegExp(req.regex, "im")

        grailbird.allTweetsObservable
          .filter(tweet => regex.test(tweet.text))
          .bufferTimedAndCounted(100.millis, 10)
          .foreach { tweets =>
            val out = WorkerMessage(message.streamId, Response.Search(tweets))
            val outBytes = Pickle.intoBytes(out)
            scope.postMessage(outBytes, js.Array(outBytes))
          }
    }
  }
}

