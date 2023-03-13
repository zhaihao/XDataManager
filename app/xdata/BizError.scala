package xdata
/*
 * Copyright (c) 2020-2023.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

/**
  * BizError
  * 业务异常，无性能问题，无jvm栈，依靠message确定异常原因，可以用于代码逻辑分支
  * @author zhaihao
  * @version 1.0
  * @since 2023/3/11 23:00
  */
class BizError private (
    message:            String,
    cause:              Throwable = null,
    enableSuppression:  Boolean = false,
    writableStackTrace: Boolean = false
) extends Exception(message, cause, enableSuppression, writableStackTrace)

object BizError {
  def apply(message: String): BizError = new BizError(message)
  def apply(message: String, cause: Throwable): BizError = new BizError(message, cause)
}
