name := "playframework-leak-prevention"

crossScalaVersions := Seq("2.13.1", "2.12.10")
scalaVersion := "2.13.1"

libraryDependencies ++= Seq(
  "com.typesafe.play" %% "play" % "2.8.0-RC1" % "provided"
)
