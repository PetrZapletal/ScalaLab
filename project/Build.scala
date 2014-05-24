import sbt._

object Version {
  val scala     = "2.10.3"
  val scalaz      = "7.0.5"
  val scalaTest = "2.0"
  val async = "0.9.0"
}

object Library {
  val async = "org.scala-lang.modules" % "scala-async_2.10" % Version.async
  val scalazCore = "org.scalaz" %% "scalaz-core" % Version.scalaz
  val scalazConcurrent = "org.scalaz" % "scalaz-concurrent_2.10" % Version.scalaz
  val scalazEffect = "org.scalaz" %% "scalaz-effect" % Version.scalaz
  val scalazTypelevel = "org.scalaz" %% "scalaz-typelevel" % Version.scalaz
  val scalazScalacheckBinding = "org.scalaz" %% "scalaz-scalacheck-binding" % Version.scalaz


  val scalaTest      = "org.scalatest"     %% "scalatest"       % Version.scalaTest
}

object Dependencies {

  import Library._

  val ScalaLab = List(
    async,
    scalazCore,
    scalazConcurrent,
    scalazEffect,
    scalazTypelevel,
    scalaTest    % "test",
    scalazScalacheckBinding % "test"
  )
}