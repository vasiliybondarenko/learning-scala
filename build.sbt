
lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.13.1",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "learning-fp",
    resolvers += Resolver.mavenLocal,


    libraryDependencies ++= Seq(
        "org.scalatest" % "scalatest_2.13" % "3.1.0" % "test"
    )

  )
