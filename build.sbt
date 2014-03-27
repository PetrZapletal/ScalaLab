name := "ScalaLab"

version := "1.0"

organization := "com.zapletal"

scalaVersion := "2.10.4"

resolvers ++= Seq("Sonatype OSS Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots",
	"snapshots-repo" at "http://scala-tools.org/repo-snapshots")

libraryDependencies += "org.scala-lang.modules" % "scala-async_2.10" % "0.9.0"

