package walfie.tweets

import org.scalajs.dom.document
import org.scalajs.dom.raw.Element
import scala.scalajs.js
import walfie.tweets.models._

object Example extends js.JSApp {
  def main(): Unit = {
    val data = js.Dynamic.global.Grailbird.data
      .asInstanceOf[js.Dictionary[js.Array[Tweet]]]

    val dataForMonth: js.Array[Tweet] = data.getOrElse("tweets_2015_12", js.Array())

    dataForMonth.foreach { tweet: Tweet =>
      val div: Element = document.createElement("div")
      div.innerHTML = tweet.text
      document.body.appendChild(div)
    }
  }
}

