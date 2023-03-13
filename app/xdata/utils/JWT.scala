/*
 * Copyright (c) 2020-2023.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

package xdata.utils

import pdi.jwt.JwtSession._
import play.api.Configuration
import play.api.mvc.RequestHeader


/**
  * JWT
  *
  * @author zhaihao
  * @version 1.0
  * @since 2023/3/12 00:04
  */
object JWT {
  def extract(requestHeader: RequestHeader)(implicit configuration: Configuration): JWT = {
    val jwt    = requestHeader.jwtSession
    val userId = jwt.getAs[Long]("id").getOrElse(0L)
    val name   = jwt.getAs[String]("name").getOrElse("")
    JWT(userId, name)
  }

  def apply(id: Long, name: String) = new JWT(id, name)
}

class JWT(_id: Long, name: String) {
  def id = _id
  override def toString = s"${_id},$name"
}

