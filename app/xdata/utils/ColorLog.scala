/*
 * Copyright (c) 2020-2023.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

package xdata.utils

/*
 * Copyright (c) 2020-2023.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

import com.typesafe.scalalogging.StrictLogging
import console.Colors
import play.api.mvc.RequestHeader

/**
  * ColorLog
  *
  * @author zhaihao
  * @version 1.0
  * @since 2023/3/11 23:04
  */
trait ColorLog extends StrictLogging {
  def log(request: RequestHeader, status: Int, userInfo: String) = {
    logger.error(
      s"request id ${Colors.blue(request.id.toString)} user ${Colors.magenta(userInfo)} ${Colors
          .yellow(request.method)} ${Colors
          .cyan(request.uri)} returned ${color(status)}"
    )
  }

  def color(status: Int): String = {
    if (status < 300) Colors.green(status.toString)
    else if (status < 400) Colors.yellow(status.toString)
    else if (status < 500) Colors.magenta(status.toString)
    else Colors.red(status.toString)
  }

  def colorCost(time: Long): String = {
    if (time < 300) Colors.green(time.toString + " ms")
    else if (time < 600) Colors.yellow(time.toString + " ms")
    else if (time < 900) Colors.magenta(time.toString + " ms")
    else Colors.red(time.toString + " ms")
  }
}
