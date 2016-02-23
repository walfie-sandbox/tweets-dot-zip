package walfie.tweets.views.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import walfie.tweets.models

object TweetIndexItem {
  case class Props(
    item: models.TweetIndexItem,
    maxTweetCount: Int
  )

  // TODO: Remove these magic values and actually write some CSS
  val component = ReactComponentB[Props]("TweetIndexItem").render_P { p: Props =>
    val percentage = p.item.tweetCount.toFloat / p.maxTweetCount

    <.div(
      ^.key := p.item.varName,
      ^.display := "inline-block",
      ^.background := "#94ddf8",
      ^.margin := "1px",
      ^.width := 20,
      ^.height := (30 * percentage).toInt
    )
  }.build

  def apply(
    item: models.TweetIndexItem,
    maxTweetCount: Int
  ): ReactElement = component(Props(item, maxTweetCount))
}

