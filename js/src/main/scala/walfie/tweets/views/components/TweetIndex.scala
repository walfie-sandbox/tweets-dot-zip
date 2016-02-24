package walfie.tweets.views.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import scalacss.ScalaCssReact._
import walfie.tweets.models
import walfie.tweets.views.styles.Styles

object TweetIndex {
  case class Props(items: Seq[models.TweetIndexMonth])

  val component = ReactComponentB[Props]("TweetIndex").render_P { p: Props =>
    val maxTweetsPerMonth: Int = p.items.map(_.tweetCount).max
    val itemsByYear: Map[Int, Seq[models.TweetIndexMonth]] = p.items.groupBy(_.year)

    // TODO: Handle case where the beginning of the year is missing
    // e.g., user's account created in December, so they have no Jan - Nov tweets
    <.div(
      itemsByYear.toSeq.sortBy(_._1).map {
        case (year: Int, monthlyItems: Seq[models.TweetIndexMonth]) =>
          <.div(
            ^.key := year,
            <.div(year, Styles.TweetIndex.yearLabel),
            <.ul(
              monthlyItems.map { item: models.TweetIndexMonth =>
                TweetIndexMonth(item, maxTweetsPerMonth)
              },
              Styles.TweetIndex.yearRow
            )
          )
      }
    )
  }.build

  def apply(items: Seq[models.TweetIndexMonth]): ReactElement =
    component(Props(items))
}

