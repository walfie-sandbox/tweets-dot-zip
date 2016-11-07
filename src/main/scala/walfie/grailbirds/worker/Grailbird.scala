package walfie.grailbirds.worker

import scala.scalajs.js
import org.scalajs.dom.raw._
import walfie.grailbirds.domain._
import js.Dynamic.{global => g, literal}
import monix.reactive.Observable

class Grailbird(scope: WorkerGlobalScope, baseUrl: String) {
  private lazy val dataByMonth = {
    g.Grailbird = g.Grailbird || literal(data = literal())
    g.Grailbird.data.asInstanceOf[js.Dictionary[js.Array[Tweet]]]
  }

  private lazy val tweetIndex = {
    scope.importScripts(js.Array(baseUrl + "data/js/tweet_index.js"))
    g.tweet_index.asInstanceOf[js.Array[TweetIndexItem]]
  }

  def data(varName: String, fileName: String): js.Array[Tweet] = {
    dataByMonth.getOrElse(varName, {
      scope.importScripts(js.Array(baseUrl + fileName))
      dataByMonth.getOrElse(varName, js.Array())
    })
  }

  def allTweetsObservable = Observable.fromIterable(tweetIndex)
    .flatMap { item: TweetIndexItem =>
      Observable.fromIterable(
        data(varName = item.varName, fileName = item.fileName)
      )
    }
}

