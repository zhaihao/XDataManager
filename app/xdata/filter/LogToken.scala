/*
 * Copyright (c) 2020-2023.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

package xdata.filter

import akka.stream.Materializer
import com.typesafe.scalalogging.StrictLogging
import console.Colors
import play.api.Configuration
import play.api.http.Status.UNAUTHORIZED
import play.api.libs.json.Json
import play.api.mvc.Results.Unauthorized
import play.api.mvc.{Filter, RequestHeader, Result}
import pdi.jwt.JwtSession._
import xdata.utils.{ColorLog, JWT}

import java.time.Clock
import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

/**
  * LogToken
  *
  * @author zhaihao
  * @version 1.0
  * @since 2023/3/11 23:58
  */
@Singleton
class LogToken @Inject() (implicit val mat: Materializer, ec: ExecutionContext, config: Configuration) extends Filter with StrictLogging with ColorLog {

  private val noAuth = config.get[Seq[String]]("play.noAuth")

  override def apply(nextFilter: RequestHeader => Future[Result])(requestHeader: RequestHeader): Future[Result] = {
    val startTime = System.currentTimeMillis

    val jwt = JWT.extract(requestHeader)

    def filterCombine = {
      nextFilter(requestHeader).map { result =>
        val endTime     = System.currentTimeMillis
        val requestTime = endTime - startTime
        val requestId   = requestHeader.id.toString

        logger.info(
          s"request id ${Colors.blue(requestId)} user ${Colors.magenta(jwt.toString)} ${Colors
              .yellow(requestHeader.method)} ${Colors
              .cyan(requestHeader.uri)} took ${colorCost(requestTime)} returned ${color(result.header.status)}"
        )
        result.withHeaders("Request-Time" -> (requestTime.toString + " ms"), "Request-Id" -> requestId)
      }
    }

    if (requestHeader.method == "OPTIONS" || noAuth.contains(requestHeader.uri)) {
      filterCombine
    } else if (jwt.id == 0) {
      log(requestHeader, UNAUTHORIZED, jwt.toString)
      Future.successful(Unauthorized(Json.obj("message" -> "用户未登陆或token已失效")))
    } else {
      filterCombine
    }
  }

}
