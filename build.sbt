scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "net.databinder.dispatch" %% "dispatch-core" % "0.11.2"
)

libraryDependencies ++= {
  val liftVersion = "2.6" // Put the current/latest lift version here
  Seq(
    "net.liftweb" %% "lift-webkit" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-mapper" % liftVersion % "compile->default",
    "net.liftweb" %% "lift-wizard" % liftVersion % "compile->default")
}
libraryDependencies += "com.github.scala-incubator.io" %% "scala-io-file" % "0.4.3"
