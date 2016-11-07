package walfie.grailbirds.worker

import scala.scalajs.js
import scala.scalajs.js.annotation._
import scala.scalajs.js.JSConverters._
import org.scalajs.dom.raw._
import walfie.grailbirds.domain._
import walfie.grailbirds.protocol._
import scala.concurrent.duration._

@JSExport("GrailbirdsWorker")
object Worker {
  @JSExport
  def init(scope: DedicatedWorkerGlobalScope, baseUrl: String): Worker =
    new Worker(scope, baseUrl)
}

class Worker(scope: DedicatedWorkerGlobalScope, baseUrl: String) {
  import monix.execution.Scheduler.Implicits.global

  val grailbird = new Grailbird(scope, baseUrl)

  scope.onmessage = { e: MessageEvent =>
    val message = e.data.asInstanceOf[ClientMessage]

    message.requestType match {
      case ClientMessage.RequestType.Search =>
        val req = message.data.asInstanceOf[ClientMessage.Search]

        val regex = js.RegExp(req.regex, "im")

        grailbird.allTweetsObservable
          .filter(tweet => regex.test(tweet.text))
          .bufferTimedAndCounted(100.millis, 10)
          .foreach { tweets =>
            val out = WorkerMessage(message.streamId, tweets.toJSArray)
            scope.postMessage(out)
          }
    }
  }
}

