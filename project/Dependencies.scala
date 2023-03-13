/*
 * Copyright (c) 2020-2022.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

import sbt._

/**
 * Dependencies
 *
 * @author zhaihao
 * @version 1.0
 * @since 2022/8/18 19:58
 */
object Dependencies extends AutoPlugin {
  override def requires = empty

  override def trigger = allRequirements

  object autoImport {

    lazy val ORISON          = "me.ooon"              %% "orison"    % "1.0.10"
    lazy val TYPESAFE_CONFIG = "com.typesafe"          % "config"    % "1.4.2"
    lazy val PLAY_JSON       = "com.typesafe.play"    %% "play-json" % "2.9.4"
    lazy val JWT             = "com.github.jwt-scala" %% "jwt-play"  % "9.2.0"

    lazy val LOG = Seq(
      "org.slf4j"                   % "log4j-over-slf4j" % "2.0.0",
      "com.typesafe.scala-logging" %% "scala-logging"    % "3.9.5",
      "ch.qos.logback"              % "logback-classic"  % "1.4.0"
    )

    lazy val SCALA_TEST = Seq(
      "org.scalatest" %% "scalatest-core"     % "3.2.15" % Test,
      "org.scalatest" %% "scalatest-freespec" % "3.2.15" % Test
    )

    lazy val overrides = Seq()

    lazy val excludes = Seq(
      "org.slf4j" % "slf4j-log4j12",
      "log4j"     % "log4j"
    )
  }
}
