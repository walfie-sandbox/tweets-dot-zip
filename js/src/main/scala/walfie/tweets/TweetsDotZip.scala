package walfie.tweets

import japgolly.scalajs.react._
import org.scalajs.dom.document
import org.scalajs.dom.raw.{Element, Event}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import walfie.tweets.models._
import walfie.tweets.util.Loader
import walfie.tweets.views.components._

object Example extends js.JSApp {
  def main(): Unit = {
    val loader = new Loader(".")
    val data = js.Dynamic.global.Grailbird.data
      .asInstanceOf[js.Dictionary[js.Array[Tweet]]]

    val contentDiv = document.createElement("div")
    document.body.appendChild(contentDiv)

    val f = for {
      files <- loader.loadTweetsIndex()
      fileData = files.find(_.fileName.contains("2015_08")).get // Testing on a large dataset. TODO: remove
      tweets <- loader.loadTweets(fileData.fileName, fileData.varName)
    } yield {
      val root = TweetList(tweets, "RT.*")
      ReactDOM.render(root, contentDiv)
    }

    f.onFailure {
      case e => js.Dynamic.global.console.error(e.toString)
    }
  }
}

