import sbt._
import sbt.Keys._
import org.scalajs.sbtplugin._
import ScalaJSPlugin.autoImport._

object Versions {
  val scalaJsReact = "0.10.4"
  val scalaCss = "0.4.0"
}

object Dependencies {
  lazy val shared = libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
  )

  lazy val jvm = libraryDependencies ++= Seq()

  lazy val js = libraryDependencies ++= Seq(
    "com.github.japgolly.scalacss" %%% "core" % Versions.scalaCss,
    "com.github.japgolly.scalacss" %%% "ext-react" % Versions.scalaCss,
    "com.github.japgolly.scalajs-react" %%% "core" % Versions.scalaJsReact,
    "com.github.japgolly.scalajs-react" %%% "extra" % Versions.scalaJsReact,
    "org.scala-js" %%% "scalajs-dom" % "0.9.0"
  )

  lazy val jsNative = jsDependencies ++= Seq(
    "org.webjars.bower" % "react" % "0.14.3"
      / "react-with-addons.js"
      minified "react-with-addons.min.js"
      commonJSName "React",

    "org.webjars.bower" % "react" % "0.14.3"
      / "react-dom.js"
      minified "react-dom.min.js"
      dependsOn "react-with-addons.js"
      commonJSName "ReactDOM"
  )
}

