ThisBuild / version := "0.8"
ThisBuild / organization := "de.skress"
ThisBuild / description := "An sbt plugin to fix java.lang.OutOfMemoryError: Metaspace/PermGen errors during interactive sbt usage."
// see: https://mvnrepository.com/artifact/com.github.dwickern/playframework-leak-prevention_2.11/0.4
ThisBuild / licenses += ("Apache-2.0", url("https://www.apache.org/licenses/LICENSE-2.0.html"))

//resolvers in ThisBuild += Resolver.bintrayRepo("skress", "maven")

val `play-plugin` = project
val `sbt-plugin` = project
val root = (project in file("."))
  .settings(skip in publish := true)
  .aggregate(`sbt-plugin`, `play-plugin`)
