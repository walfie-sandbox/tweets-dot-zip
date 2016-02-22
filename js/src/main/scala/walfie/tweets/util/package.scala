package walfie.tweets

import scala.concurrent.{Future, Promise}
import org.scalajs.dom.document
import org.scalajs.dom.raw.{Event, HTMLScriptElement}

package object util {
  def loadJs(url: String): Future[Event] = {
    val p: Promise[Event] = Promise()

    val script = document.createElement("script").asInstanceOf[HTMLScriptElement]
    script.src = url
    script.onload = (e: Event) => p.success(e)
    document.body.appendChild(script)

    p.future
  }
}

