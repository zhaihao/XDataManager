/*
 * Copyright (c) 2020-2023.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */
package xdata.controller

import play.api.mvc.{AbstractController, ControllerComponents}

import javax.inject.{Inject, Singleton}


/**
  * HealthCheck
  *
  * @author zhaihao
  * @version 1.0
  * @since 2023/3/11 21:17
  */
@Singleton
class HealthCheckCC @Inject()(cc: ControllerComponents) extends AbstractController(cc) {
  def test = Action {
    Ok
  }
}
