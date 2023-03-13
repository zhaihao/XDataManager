/*
 * Copyright (c) 2020-2023.
 * OOON.ME ALL RIGHTS RESERVED.
 * Licensed under the Mozilla Public License, version 2.0
 * Please visit <http://ooon.me> or mail to zhaihao@ooon.me
 */

package xdata

import java.time.Clock

/**
  * package
  *
  * @author zhaihao
  * @version 1.0
  * @since 2023/3/12 00:14
  */
package object utils {
  implicit val clock = Clock.systemUTC
}
