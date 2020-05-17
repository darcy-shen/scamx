package net.xmacs.guava.math

import net.xmacs.guava.primitives.Ints

object IntMath {
  /**
    * Returns the sum of {@code a} and {@code b} unless it would overflow or underflow in which case
    * {@code Integer.MAX_VALUE} or {@code Integer.MIN_VALUE} is returned, respectively.
    *
    * @since 20.0
    */
  def saturatedAdd(a: Int, b: Int): Int = Ints.saturatedCast(a.toLong + b)
}
