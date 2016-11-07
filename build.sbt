lazy val commonSettings = Seq(
  scalaVersion := "2.11.8",
  organization := "com.github.walfie",
  name := "grailbirds",
  version := "0.0.1-SNAPSHOT",
  scalacOptions ++= Seq("-deprecation", "-feature", "-unchecked", "-Xlint"),
  Scalariform.settings
)

val jsPath = settingKey[File]("Output directory for scala.js compiled files")
lazy val root = (project in file("."))
  .enablePlugins(ScalaJSPlugin)
  .settings(commonSettings: _*)
  .settings(
    // Put output JS files in `target/scala-2.11/classes/public/`
    jsPath := crossTarget.value / "classes" / "public",
    crossTarget in (Compile, fullOptJS) := jsPath.value,
    crossTarget in (Compile, fastOptJS) := jsPath.value,
    crossTarget in (Compile, packageJSDependencies) := jsPath.value,
    crossTarget in (Compile, packageScalaJSLauncher) := jsPath.value,
    crossTarget in (Compile, packageMinifiedJSDependencies) := jsPath.value,

    libraryDependencies ++= Seq(
      "com.thoughtworks.binding" %%% "dom" % "9.0.4",
      "io.monix" %%% "monix" % "2.0.6",
      "org.scala-js" %%% "scalajs-dom" % "0.9.1"
    )
  )

