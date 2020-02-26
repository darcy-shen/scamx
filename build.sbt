name := "sguava"
version := "0.1.0-SNAPSHOT"
// scalaVersion := "2.13.1"
scalaVersion := System.getProperty("scala.version", "0.22.0-RC1")

scalacOptions := Seq("-Yno-predef")

libraryDependencies ++= Seq(
  // "org.scala-lang.modules" %% "scala-java8-compat" % "0.9.1",
  "org.scalatest" %% "scalatest" % "3.1.1" % Test
)
// Set to false or remove if you want to show stubs as linking errors
// nativeLinkStubs := true

// enablePlugins(ScalaNativePlugin)
