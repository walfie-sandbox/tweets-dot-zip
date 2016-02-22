package walfie.tweets.views.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.prefix_<^._
import walfie.tweets.models

object TweetList {
  case class Props(
    tweets: Seq[models.Tweet],
    regex: String
  )

  val component = ReactComponentB[Props]("TweetList").render_P { p: Props =>
    <.ul(
      for {
        tweet <- p.tweets if tweet.text.matches(p.regex)
      } yield {
        <.li(Tweet(tweet))
      }
    )
  }.build

  def apply(
    tweets: Seq[models.Tweet],
    regex: String
  ): ReactElement = component(Props(tweets, regex))
}

