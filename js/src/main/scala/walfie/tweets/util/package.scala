package walfie.tweets

import org.scalajs.dom.document
import org.scalajs.dom.raw.{Event, HTMLScriptElement}

package object util {
  def loadJs(url: String)(onLoad: Event => Unit): Unit = {
    val script = document.createElement("script").asInstanceOf[HTMLScriptElement]
    script.src = url
    script.onload = onLoad
    document.body.appendChild(script)
  }
}

