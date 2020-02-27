package net.xmacs.base

import net.xmacs._

/** Implementation of an {@link Optional} containing a reference. */
final class Present[T](val reference: T) extends Optional[T] {
  override def isPresent = true

  override def orNull: T = reference

  override def get: T = reference

  override def asSet: Set[T] = ???
}
