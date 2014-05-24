name := "ScalaLab"

version := "1.0"

scalaVersion := Version.scala

libraryDependencies ++= Dependencies.ScalaLab

scalacOptions ++= List(
  "-unchecked",
  "-deprecation",
  "-language:_",
  "-target:jvm-1.7",
  "-encoding", "UTF-8"
)

//resolvers ++= Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
//	"snapshots-repo" at "http://scala-tools.org/repo-snapshots")

//initialCommands in console := "import scalaz._, Scalaz._"

//initialCommands in console in Test := "import scalaz._, Scalaz._,scalacheck.ScalazProperties._,scalacheck.ScalazArbitrary._,scalacheck.ScalaCheckBinding._"












