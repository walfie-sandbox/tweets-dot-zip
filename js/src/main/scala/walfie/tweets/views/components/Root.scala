package walfie.tweets.views.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import walfie.tweets.models

object Root {
  case class Props(
    tweetIndex: Seq[models.TweetIndexMonth],
    tweets: Seq[models.Tweet]
  )

  val component = ReactComponentB[Props]("Root").render_P { p: Props =>
    <.div(
      TweetIndex(p.tweetIndex),
      TweetList(p.tweets, "")
    )
  }.build

  def apply(
    tweetIndex: Seq[models.TweetIndexMonth],
    tweets: Seq[models.Tweet]
  ): ReactElement = component(Props(tweetIndex, tweets))
}

