sbtPlugin := true

name := "sbt-classloader-leak-prevention"

libraryDependencies ++= Seq(
  "se.jiderhamn.classloader-leak-prevention" % "classloader-leak-prevention-core" % "2.2.0",
  "se.jiderhamn" % "classloader-leak-test-framework" % "1.1.1"
)

addSbtPlugin("com.typesafe.play" % "sbt-plugin" % "2.6.15")

//val `play-plugin` = project in file("../play-plugin")

lazy val `play-plugin` = RootProject(file("../play-plugin"))

val rot = (project in file("."))
  .settings(
    organization := "cerno"
  ).dependsOn(`play-plugin`) 
  

enablePlugins(BuildInfoPlugin)
buildInfoPackage := "com.github.dwickern"
buildInfoKeys := Seq[BuildInfoKey](
  BuildInfoKey.map(organization in `play-plugin`)("playPluginOrganization" -> _._2),
  BuildInfoKey.map(name in `play-plugin`)("playPluginName" -> _._2),
  BuildInfoKey.map(version in `play-plugin`)("playPluginVersion" -> _._2)
)
enablePlugins(ScriptedPlugin)
scriptedLaunchOpts := Seq(s"-Dplugin.version=${version.value}")
scriptedRun := scriptedRun.dependsOn(publishLocal in `play-plugin`).value
scriptedBufferLog := false
