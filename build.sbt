name := "scamx"

lazy val commonSettings = Seq(
  organization := "com.sadhen.binding",
  version := "0.0.1-SNAPSHOT",
  scalaVersion := System.getProperty("scala.version", "2.13.1"),
  scalacOptions := Seq("-Yno-predef"),
  libraryDependencies ++= Seq(
    // "org.scala-lang.modules" %% "scala-java8-compat" % "0.9.1",
    "org.scalatest" %% "scalatest" % "3.1.1" % Test
  )
)

lazy val root = (project in file("."))
  .aggregate(
    scamx_core,
    guava_base,
  )
  .settings(
    name := "Binding-SemanticUI"
  )

lazy val scamx_core = (project in file("scamx-core"))
  .settings(commonSettings)

lazy val guava_base = (project in file("guava-base"))
  .settings(commonSettings)
  .dependsOn(scamx_core)

lazy val guava_collect = (project in file("guava-collect"))
  .settings(commonSettings)

lazy val guava_concurrent = (project in file("guava-concurrent"))
  .settings(commonSettings)

lazy val guava_primitives = (project in file("guava-primitives"))
  .settings(commonSettings)

// Set to false or remove if you want to show stubs as linking errors
// nativeLinkStubs := true

// enablePlugins(ScalaNativePlugin)
