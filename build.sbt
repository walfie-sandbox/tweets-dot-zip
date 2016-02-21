lazy val projectSettings = Seq(
  scalaVersion := "2.11.7",
  name := "tweets dot zip",
  organization := "walfie.tweets",
  version := "0.0.1-SNAPSHOT"
)

lazy val root = project
  .in(file("."))
  .aggregate(appJS, appJVM)

lazy val app = crossProject
  .in(file("."))
  .settings(projectSettings ++ Settings.shared: _*)
  .jvmSettings(Settings.jvm: _*)
  .jsSettings(Settings.js: _*)

lazy val appJVM = app.jvm
lazy val appJS = app.js

