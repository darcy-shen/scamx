package net.xmacs.guava.collect

import java.util

object Collections2 {
  /** Used to avoid http://bugs.sun.com/view_bug.do?bug_id=6558557 */
  def cast[T](iterable: Iterable[T]): util.Collection[T] =
    iterable.asInstanceOf[util.Collection[T]]
}
