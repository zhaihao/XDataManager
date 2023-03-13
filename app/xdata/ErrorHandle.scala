package xdata
import com.typesafe.scalalogging.StrictLogging
import console.Colors
import play.api.http.HttpErrorHandler
import play.api.libs.json.{JsResultException, Json}
import play.api.mvc.Results.{BadRequest, InternalServerError}
import play.api.mvc.{RequestHeader, Results}
import play.api.{Configuration, Environment, Mode}
import utils.{ColorLog, JWT}

import javax.inject.Singleton
import javax.inject.Inject
import scala.concurrent.Future

/*
 * Copyright (c) 2020-2023.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

/**
  * ErrorHandle
  *
  * @author zhaihao
  * @version 1.0
  * @since 2023/3/11 22:58
  */
//noinspection ScalaUnusedSymbol
@Singleton
class ErrorHandle @Inject() (env: Environment)(implicit configuration: Configuration) extends HttpErrorHandler with StrictLogging with ColorLog{
  override def onServerError(request: RequestHeader, exception: Throwable) = {
    // server 端会解析两次 jwt，暂时无法避免
    val jwt = JWT.extract(request).toString
    logger.error(
      s"request id ${Colors.blue(request.id.toString)} user ${Colors.magenta(jwt)} occurs server exception:",
      exception
    )

    exception match {
      case e: BizError =>
        log(request, 299, jwt)
        Future.successful(Results.Status(299)(Json.obj("message" -> e.getMessage)))
      case e: JsResultException =>
        log(request, 400, jwt)
        val errField = e.errors.head
        if (env.mode == Mode.Prod) {
          Future.successful(BadRequest(Json.obj("message" -> "请求 json 不正确")))
        } else {
          Future.successful(
            BadRequest(Json.obj("message" -> (errField._2.head.messages.head + ": " + errField._1.toString())))
          )
        }
      case e: Exception =>
        if (env.mode == Mode.Prod) {
          Future.successful(InternalServerError(Json.obj("message" -> "服务器内部错误")))
        } else {
          Future.successful(InternalServerError(Json.obj("message" -> e.getMessage)))
        }

    }
  }

  override def onClientError(request: RequestHeader, statusCode: Int, message: String) = {
    statusCode match {
      case 404 =>
        Future.successful(
          Results.Status(statusCode)(Json.obj("message" -> s"${request.uri} Not Found"))
        )

      case 415 =>
        Future.successful(
          Results.Status(statusCode)(
            Json.obj("message" -> s"${request.headers.get("Content-Type")} not support")
          )
        )

      case _ => Future.successful(Results.Status(statusCode)(Json.obj("message" -> message)))
    }
  }
}
