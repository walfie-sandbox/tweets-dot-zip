import sbt._
import sbt.Keys._
import org.scalajs.sbtplugin._
import ScalaJSPlugin.autoImport._

object Dependencies {
  lazy val shared = libraryDependencies ++= Seq(
    "com.lihaoyi" %%% "utest" % "0.3.1" % "test"
  )

  lazy val jvm = libraryDependencies ++= Seq()

  lazy val js = libraryDependencies ++= Seq(
    "org.scala-js" %%% "scalajs-dom" % "0.8.2"
  )

  lazy val jsNative = jsDependencies ++= Seq()
}

