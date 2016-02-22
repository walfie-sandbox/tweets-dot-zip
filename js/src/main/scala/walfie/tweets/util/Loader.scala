package walfie.tweets.util

import org.scalajs.dom.document
import org.scalajs.dom.raw.{Event, HTMLScriptElement}
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.{Future, Promise}
import scala.scalajs.js
import walfie.tweets.models._

class Loader(baseUrl: String = ".") {
  private val _baseUrl = if (baseUrl.isEmpty) "." else baseUrl

  private def loadJs(path: String): Future[Event] = {
    val p: Promise[Event] = Promise()

    val script = document.createElement("script").asInstanceOf[HTMLScriptElement]
    script.src = _baseUrl + "/" + path
    script.onload = (e: Event) => p.success(e)
    document.body.appendChild(script)

    p.future
  }

  private def loadDynamic[T](path: String, dynamic: () => js.Dynamic): Future[T] = {
    def dynamicOpt = dynamic().asInstanceOf[js.UndefOr[T]]

    dynamicOpt.fold {
      loadJs(path).map { _ =>
        dynamicOpt.getOrElse {
          throw new NoSuchElementException("Could not load " + path)
        }
      }
    }(Future.successful)
  }

  def loadTweetsIndex(): Future[js.Array[TweetIndexItem]] = {
    loadDynamic("data/js/tweet_index.js", () => js.Dynamic.global.tweet_index)
  }

  def loadTweets(fileName: String, varName: String): Future[js.Array[Tweet]] = {
    val data = js.Dynamic.global.Grailbird.data
      .asInstanceOf[js.Dictionary[js.Array[Tweet]]]

    data.get(varName) match {
      case Some(v) => Future.successful(v)
      case None => loadJs(fileName).map { _ =>
        data.getOrElse(varName, throw new NoSuchElementException("Could not find " + varName))
      }
    }
  }

}

