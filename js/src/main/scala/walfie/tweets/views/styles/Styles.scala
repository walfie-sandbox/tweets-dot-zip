package walfie.tweets.views.styles

import scalacss.Defaults._

object Styles extends StyleSheet.Inline {
  import dsl._

  val cssReset = style(scalacss.ext.CssReset.normaliseCss)

  object TweetIndex {
    val monthContainer = style(
      backgroundColor(c"#f3f3f3"),
      margin(1.px),
      width(20.px),
      height(100.%%),
      position.relative,
      display.inlineBlock
    )

    val monthFilled = style(
      backgroundColor(c"#94ddf8"),
      position.absolute,
      width(100.%%),
      bottom(0.px)
    )

    val yearLabel = style(
      padding(5.px)
    )

    val yearRow = style(
      height(30.px),
      listStyleType := none
    )
  }

  initInnerObjects(TweetIndex.monthContainer)
}

