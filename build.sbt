name := "scalacheck-tags"

organization := "com.noelmarkham"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.5"

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2"

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases"  at nexus + "service/local/staging/deploy/maven2")
}

publishArtifact in Test := false

pomIncludeRepository := { _ => false }

pomExtra := <url>https://github.com/noelmarkham/scalacheck-tags</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://www.opensource.org/licenses/mit-license.php</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <scm>
    <url>git@github.com/noelmarkham/scalacheck-tags.git</url>
    <connection>scm:git:git@github.com/noelmarkham/scalacheck-tags.git</connection>
  </scm>
  <developers>
    <developer>
      <id>noelmarkham</id>
      <name>Noel Markham</name>
      <url>http://github.com/noelmarkham</url>
    </developer>
  </developers>