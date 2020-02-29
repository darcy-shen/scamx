package net.xmacs.guava.base

import java.util.function.Function

import net.xmacs._

/** Implementation of an {@link Optional} containing a reference. */
final class Present[T](val reference: T) extends Optional[T] {
  override def isPresent = true

  override def orNull: T = reference

  override def get: T = reference

  override def asSet: Set[T] = ???

  override def transform[V](function: Function[T, V]): Optional[V] = ???

  override def equals(obj: Any): Boolean = ???

  override def hashCode(): Int = ???

  override def toString: String = ???
}
