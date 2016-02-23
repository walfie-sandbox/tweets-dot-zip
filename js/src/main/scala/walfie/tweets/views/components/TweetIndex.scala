package walfie.tweets.views.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import walfie.tweets.models

object TweetIndex {
  case class Props(items: Seq[models.TweetIndexItem])

  val component = ReactComponentB[Props]("TweetIndex").render_P { p: Props =>
    val maxTweetsPerMonth: Int = p.items.map(_.tweetCount).max
    val itemsByYear: Map[Int, Seq[models.TweetIndexItem]] = p.items.groupBy(_.year)

    // TODO: Handle case where the beginning of the year is missing
    // e.g., user's account created in December, so they have no Jan - Nov tweets
    <.div(
      itemsByYear.toSeq.sortBy(_._1).map {
        case (year: Int, monthlyItems: Seq[models.TweetIndexItem]) =>
          <.div(
            ^.key := year,
            <.span(year),
            monthlyItems.map { item: models.TweetIndexItem =>
              TweetIndexItem(item, maxTweetsPerMonth)
            }
          )
      }
    )
  }.build

  def apply(items: Seq[models.TweetIndexItem]): ReactElement =
    component(Props(items))
}

