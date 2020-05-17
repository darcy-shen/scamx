package net.xmacs.guava.collect

import java.util
import net.xmacs.guava.base.Preconditions._

object Iterators {

  /**
    * Adds all elements in {@code iterator} to {@code collection}. The iterator will be left
    * exhausted: its {@code hasNext()} method will return {@code false}.
    *
    * @return { @code true} if { @code collection} was modified as a result of this operation
    */
  def addAll[T](addTo: util.Collection[T], iterator: Iterator[_ <: T]): Boolean = {
    checkNotNull(addTo)
    checkNotNull(iterator)
    var wasModified = false
    while (iterator.hasNext) {
      wasModified |= addTo.add(iterator.next)
    }
    wasModified
  }
}
