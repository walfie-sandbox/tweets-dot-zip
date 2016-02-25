package walfie.tweets

import japgolly.scalajs.react._
import org.scalajs.dom.document
import org.scalajs.dom.raw.{Element, Event, Worker}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scalacss.Defaults._
import scalacss.ScalaCssReact._
import walfie.tweets.models._
import walfie.tweets.util.Loader
import walfie.tweets.views.components._
import walfie.tweets.views.styles.Styles

object TweetsDotZip extends js.JSApp {
  def main(): Unit = {
    /*
    Styles.addToDocument()
    val loader = new Loader(".")

    val contentDiv = document.createElement("div")
    document.body.appendChild(contentDiv)

    val f = for {
      files <- loader.loadTweetsIndex()
      fileData = files.head // TODO: Allow month selection
      tweets <- loader.loadTweets(fileData.fileName, fileData.varName)
    } yield {
      val root = Root(files, tweets)
      ReactDOM.render(root, contentDiv)
    }

    f.onFailure {
      case e => js.Dynamic.global.console.error(e.toString)
    }
    */

    val worker = new Worker("worker.js")
  }

  @JSExport
  def workerMain(): Unit = {
    val loader = new Loader(".")

    val f = for {
      files <- loader.loadTweetsIndex()
      fileData = files.head // TODO: Allow month selection
      tweets <- loader.loadTweets(fileData.fileName, fileData.varName)
    } yield {
      println(tweets.head.text)
    }
  }
}

