Global / lintUnusedKeysOnLoad := false

scalaVersion := "2.13.8"
name         := "XDataManage"
organization := "me.ooon"
target       := studioTarget.value
maintainer   := "zhaihao@ooon.me"

onLoadMessage := ""

enablePlugins(PlayLayoutPlugin, PlayService, PlayNettyServer)

libraryDependencies ++= Seq(ORISON, TYPESAFE_CONFIG, PLAY_JSON, JWT, guice)
libraryDependencies ++= Seq(SCALA_TEST, LOG).flatten

Global / excludeDependencies ++= excludes
Global / dependencyOverrides ++= overrides

Compile / unmanagedResourceDirectories += (Compile / resourceDirectory).value.getParentFile / "common"
Compile / resourceDirectory            := (Compile / resourceDirectory).value / env.value.toString