package walfie.tweets

import org.scalajs.dom.document
import org.scalajs.dom.raw.Element
import scala.scalajs.js
import walfie.tweets.models._
import walfie.tweets.util.Loader
import scala.concurrent.ExecutionContext.Implicits.global

object Example extends js.JSApp {
  def main(): Unit = {
    val loader = new Loader(".")
    val data = js.Dynamic.global.Grailbird.data
      .asInstanceOf[js.Dictionary[js.Array[Tweet]]]

    val f = for {
      files <- loader.loadTweetsIndex()
      fileData = files.head
      tweets <- loader.loadTweets(fileData.fileName, fileData.varName)
    } yield {
      val tweetsByHour: Map[Int, Int] = tweets
        .groupBy(_.createdAtDate.getHours)
        .mapValues(_.size)

      val table = document.createElement("table")
      document.body.appendChild(table)

      tweetsByHour.toSeq.sortBy(_._1).foreach {
        case (hour: Int, count: Int) =>
          val row = document.createElement("tr")
          val hourCell = document.createElement("td")
          hourCell.innerHTML = hour.toString
          val countCell = document.createElement("td")
          countCell.innerHTML = count.toString

          row.appendChild(hourCell)
          row.appendChild(countCell)
          table.appendChild(row)
      }
    }

    f.onFailure {
      case e => println(e)
    }
  }
}

