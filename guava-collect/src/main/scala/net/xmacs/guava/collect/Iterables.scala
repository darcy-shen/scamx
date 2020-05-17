package net.xmacs.guava.collect

import java.util
import net.xmacs.guava.base.Preconditions._

object Iterables {

  /**
    * Adds all elements in {@code iterable} to {@code collection}.
    *
    * @return { @code true} if { @code collection} was modified as a result of this operation.
    */
  def addAll[T](addTo: util.Collection[T], elementsToAdd: Iterable[_ <: T]): Boolean = {
    if (elementsToAdd.isInstanceOf[util.Collection[_]]) {
      val c = Collections2.cast(elementsToAdd)
      return addTo.addAll(c)
    }
    Iterators.addAll(addTo, checkNotNull(elementsToAdd).iterator)
  }
}
