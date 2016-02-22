package walfie.tweets.views.components

import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.vdom.prefix_<^._
import walfie.tweets.models

object Tweet {
  type Props = models.Tweet

  implicit val propsReuse = Reusability.by((_: Props).id)

  val component = ReactComponentB[Props]("Tweet")
    .render_P { p: Props =>
      <.div(p.text)
    }
    .configure(Reusability.shouldComponentUpdate)
    .build

  def apply(p: Props): ReactElement = component(p)
}

