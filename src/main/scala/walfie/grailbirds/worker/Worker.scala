package walfie.grailbirds.worker

import scala.scalajs.js
import scala.scalajs.js.annotation._
import org.scalajs.dom.raw._
import walfie.grailbirds.domain._

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
    val searchTerm = e.data.asInstanceOf[String]
    val regex = js.RegExp(searchTerm, "im")

    grailbird.allTweetsObservable
      .filter(tweet => regex.test(tweet.text))
      .foreach(tweet => scope.postMessage(tweet))
  }
}

