package walfie.tweets.views.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import scalacss.ScalaCssReact._
import walfie.tweets.models
import walfie.tweets.views.styles.Styles

object TweetIndexMonth {
  case class Props(
    item: models.TweetIndexMonth,
    maxTweetCount: Int
  )

  // TODO: Remove these magic values and actually write some CSS
  val component = ReactComponentB[Props]("TweetIndexMonth").render_P { p: Props =>
    val percentage = p.item.tweetCount.toFloat / p.maxTweetCount

    <.li(
      Styles.TweetIndex.monthContainer,
      <.div(
        ^.key := p.item.varName,
        Styles.TweetIndex.monthFilled,
        ^.height := s"${percentage * 100}%"
      )
    )
  }.build

  def apply(
    item: models.TweetIndexMonth,
    maxTweetCount: Int
  ): ReactElement = component(Props(item, maxTweetCount))
}

