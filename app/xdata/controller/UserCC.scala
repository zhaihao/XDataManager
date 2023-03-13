/*
 * Copyright (c) 2020-2023.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

package xdata.controller

import pdi.jwt.JwtSession._
import play.api.Configuration
import play.api.mvc.{AbstractController, ControllerComponents}
import xdata.BizError
import xdata.utils.clock

import javax.inject.{Inject, Singleton}
import scala.concurrent.{ExecutionContext, Future}

/**
  * UserCC
  *
  * @author zhaihao
  * @version 1.0
  * @since 2023/3/12 00:34
  */
@Singleton
class UserCC @Inject() (cc: ControllerComponents)(implicit ec: ExecutionContext, configuration: Configuration) extends AbstractController(cc) {
  def login = Action.async(parse.json) { req =>
    1/0
    Future(Ok.withJwtSession(("id", 1), ("name", "zhaihao")))
  }

  // 使用 jwt 服务端什么也做不了，只能客户端删除 token，除非服务端也存储 token
  def logout() = Action {
    Ok.withoutJwtSession
  }
}
