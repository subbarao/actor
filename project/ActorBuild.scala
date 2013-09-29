import sbt._
import sbt.Keys._

object ActorBuild extends Build {

  lazy val actor = Project(
    id = "actor",
    base = file("."),
    settings = Project.defaultSettings ++ Seq(
      name := "Actor",
      organization := "org.bigbinary",
      version := "0.1-SNAPSHOT",
      scalaVersion := "2.10.2",
      libraryDependencies ++= Seq(
        "org.apache.commons" % "commons-lang3" % "3.1",
        "net.sourceforge.htmlcleaner" % "htmlcleaner" % "2.6",
        "org.mongodb" %% "casbah" % "2.6.3",
        "com.typesafe.akka" %% "akka-actor" % "2.2.1")))
}
