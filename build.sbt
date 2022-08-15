organization := "com.typesafe.akka"
name := "akka-stream-contrib"

crossScalaVersions := Seq("2.12.10", "2.13.0")
scalaVersion := crossScalaVersions.value.head

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % versions.akka,
  "com.typesafe.akka" %% "akka-stream-testkit" % versions.akka % Test,
  "junit" % "junit" % versions.junit % Test, // Common Public License 1.0
  "com.novocode" % "junit-interface" % versions.junitIntf % Test, // BSD-like
  "com.google.jimfs" % "jimfs" % versions.jimfs % Test, // ApacheV2
  "org.scalatest" %% "scalatest" % versions.scalaTest % Test, // ApacheV2
  "org.scalamock" %% "scalamock" % versions.scalaMock % Test, // ApacheV2
  "com.miguno.akka" %% "akka-mock-scheduler" % versions.akkamocksch % Test // ApacheV2
)

organizationName := "Lightbend Inc."
organizationHomepage := Some(url("http://www.lightbend.com"))
homepage := Some(url("https://github.com/akka/akka-stream-contrib"))
licenses := Seq(("Apache License, Version 2.0", url("http://www.apache.org/licenses/LICENSE-2.0")))
scmInfo := Some(
  ScmInfo(url("https://github.com/akka/akka-stream-contrib"), "git@github.com:akka/akka-stream-contrib.git")
)
developers += Developer("contributors",
                        "Contributors",
                        "https://gitter.im/akka/dev",
                        url("https://github.com/akka/akka-stream-contrib/graphs/contributors"))

scalacOptions ++=
  Seq("-encoding", "UTF-8", "-feature", "-unchecked", "-deprecation", "-Xlint") ++ (
    if (scalaVersion.value startsWith "2.13.")
      Seq(
        "-Wdead-code",
        "-Wnumeric-widen",
        "-Xsource:2.14"
      )
    else
      Seq(
        //"-Xfatal-warnings",
        "-Xlint",
        "-Yno-adapted-args",
        "-Ywarn-dead-code",
        "-Ywarn-numeric-widen",
        "-Xfuture"
      )
  )

// By default scalatest futures time out in 150 ms, dilate that to 600ms.
// This should not impact the total test time as we don't expect to hit this
// timeout, and indeed it doesn't appear to.
Test / testOptions += Tests.Argument(TestFrameworks.ScalaTest, "-F", "4")

// show full stack traces and test case durations
Test / testOptions += Tests.Argument("-oDF")

// -v Log "test run started" / "test started" / "test run finished" events on log level "info" instead of "debug".
// -a Show stack traces and exception class name for AssertionErrors.
testOptions += Tests.Argument(TestFrameworks.JUnit, "-v", "-a")

enablePlugins(AutomateHeaderPlugin)
headerLicense := Some(HeaderLicense.Custom(s"Copyright (C) 2016 Lightbend Inc. <http://www.lightbend.com>"))
scalafmtOnCompile := true
