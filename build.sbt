organization := "com.example"

name := "bitshow"

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
   "net.databinder" %% "unfiltered-directives" % "0.8.0",
   "net.databinder" %% "unfiltered-filter" % "0.8.0",
   "net.databinder" %% "unfiltered-jetty" % "0.8.0",
   "net.databinder" %% "unfiltered-uploads" % "0.8.0",
   "net.databinder" %% "unfiltered-netty-uploads" % "0.7.0",
   "io.argonaut" %% "argonaut" % "6.0.4",
   "io.argonaut" %% "argonaut-unfiltered" % "6.0.4",
   "org.clapper" % "avsl_2.10" % "1.0",
   "org.processing" % "core" % "1.1",
   "org.mongodb" %% "casbah" % "2.7.2",
   "net.liftweb" %% "lift-json" % "2.5.1",
   "io.spray" %%  "spray-json" % "1.2.6",
   "commons-io" % "commons-io" % "2.3"
)

resolvers ++= Seq(
  "java m2" at "http://download.java.net/maven/2",
  "tech" at "http://databinder.net/repo/",
  "scala snapshots" at "http://scala-tools.org/repo-snapshots"
)
